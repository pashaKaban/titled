import java.io.*;
import java.net.Socket;
public class Client
{
    public static String IPADRESS="localhost";
    public static int PORT=7800;
    public static void main(String []args)
    {
        try
        {
            Socket socket = new Socket(IPADRESS, PORT);//create socket for client
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream());

                int size = (int) in.readObject();
                System.out.println(size);

            String messege="Are you ready?";
            out.writeObject(messege);
            out.flush();
            byte[]array=new byte[size];
            array=(byte[])in.readObject();
            System.out.println("writing");
            FileOutputStream fs = new FileOutputStream("C:/success/copysign.jpg");
            fs.write(array);
            fs.close();
        }
        catch(ClassNotFoundException e)
        {e.printStackTrace();}
        catch (IOException e)
        {e.printStackTrace();}
    }
}
