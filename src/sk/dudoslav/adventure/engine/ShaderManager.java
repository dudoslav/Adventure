package sk.dudoslav.adventure.engine;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by dusan on 13.08.2015.
 */
public class ShaderManager {

    private int vs;  //Vertex Shader
    private int fs;  //Fragment Shader
    private int sp;  //Shader Program

    public void useShader(){
        ARBShaderObjects.glUseProgramObjectARB(sp);
    }

    public void releaseShader(){
        ARBShaderObjects.glUseProgramObjectARB(0);
    }

    public void loadVertexShader(String filename) throws Exception{
        vs = createShader(filename, ARBVertexShader.GL_VERTEX_SHADER_ARB);
    }

    public void loadFragmentShader(String filename) throws Exception{
        fs = createShader(filename, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
    }

    public void createShaderProgram() throws Exception{
        sp = ARBShaderObjects.glCreateProgramObjectARB();

        if(sp == 0) throw new Exception("Failed to create shader program: " + getLogInfo(sp));

        ARBShaderObjects.glAttachObjectARB(sp, vs);
        ARBShaderObjects.glAttachObjectARB(sp, fs);

        ARBShaderObjects.glLinkProgramARB(sp);
        if (ARBShaderObjects.glGetObjectParameteriARB(sp, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(sp));
            throw new Exception("Error in shader program: " + getLogInfo(sp));
        }

        ARBShaderObjects.glValidateProgramARB(sp);
        if (ARBShaderObjects.glGetObjectParameteriARB(sp, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(sp));
            throw new Exception("Error in shader program: " + getLogInfo(sp));
        }
    }

    private String readFileAsString(String filename) throws Exception{
        StringBuilder source = new StringBuilder();

        FileInputStream in = new FileInputStream(filename);

        Exception exception = null;

        BufferedReader reader;
        try{
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));

            Exception innerExc= null;
            try {
                String line;
                while((line = reader.readLine()) != null)
                    source.append(line).append('\n');
            }
            catch(Exception exc) {
                exception = exc;
            }
            finally {
                try {
                    reader.close();
                }
                catch(Exception exc) {
                    if(innerExc == null)
                        innerExc = exc;
                    else
                        exc.printStackTrace();
                }
            }

            if(innerExc != null)
                throw innerExc;
        }
        catch(Exception exc) {
            exception = exc;
        }
        finally {
            try {
                in.close();
            }
            catch(Exception exc) {
                if(exception == null)
                    exception = exc;
                else
                    exc.printStackTrace();
            }

            if(exception != null)
                throw exception;
        }

        return source.toString();
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
        ARBShaderObjects.glUseProgramObjectARB(sp);
    }

    public int getSp() {
        return sp;
    }
}
