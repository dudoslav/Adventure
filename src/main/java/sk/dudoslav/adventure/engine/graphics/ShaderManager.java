package sk.dudoslav.adventure.engine.graphics;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by dusan on 13.08.2015.
 */
public class ShaderManager {

    private int vertexShaderHandle;
    private int fragmentShaderHandle;
    private int shaderProgramHandle;

    public void useShader(){
        ARBShaderObjects.glUseProgramObjectARB(shaderProgramHandle);
    }

    public void releaseShader(){
        ARBShaderObjects.glUseProgramObjectARB(0);
    }

    public void loadVertexShader(String filename) throws Exception{
        vertexShaderHandle = createShader(filename, ARBVertexShader.GL_VERTEX_SHADER_ARB);
    }

    public void loadFragmentShader(String filename) throws Exception{
        fragmentShaderHandle = createShader(filename, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
    }

    public void createShaderProgram() throws Exception{
        shaderProgramHandle = ARBShaderObjects.glCreateProgramObjectARB();

        if(shaderProgramHandle == 0) throw new Exception("Failed to create shader program: " + getLogInfo(shaderProgramHandle));

        ARBShaderObjects.glAttachObjectARB(shaderProgramHandle, vertexShaderHandle);
        ARBShaderObjects.glAttachObjectARB(shaderProgramHandle, fragmentShaderHandle);

        ARBShaderObjects.glLinkProgramARB(shaderProgramHandle);
        if (ARBShaderObjects.glGetObjectParameteriARB(shaderProgramHandle, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(shaderProgramHandle));
            throw new Exception("Error in shader program: " + getLogInfo(shaderProgramHandle));
        }

        ARBShaderObjects.glValidateProgramARB(shaderProgramHandle);
        if (ARBShaderObjects.glGetObjectParameteriARB(shaderProgramHandle, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(shaderProgramHandle));
            throw new Exception("Error in shader program: " + getLogInfo(shaderProgramHandle));
        }
    }

    private String readFileAsString(String filename) throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for(String line: lines){
            stringBuilder.append(line).append('\n');
        }
        return stringBuilder.toString();
    }

    private static String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }

    private int createShader(String filename, int shaderType) throws Exception{
        int shader = 0;
        try{
            shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
            if(shader == 0) return 0;
            ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(filename));
            ARBShaderObjects.glCompileShaderARB(shader);

            if(ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
                throw new RuntimeException("Error creating shader: " + getLogInfo(shader));

            return shader;
        }catch (Exception e){
            ARBShaderObjects.glDeleteObjectARB(shader);
            throw e;
        }
    }

    public void useProgram(){
        ARBShaderObjects.glUseProgramObjectARB(shaderProgramHandle);
    }

    public int getShaderProgramHandle() {
        return shaderProgramHandle;
    }
}
