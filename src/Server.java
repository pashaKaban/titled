import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.*;//OutputStream;
import java.net.*;//ServerSocket;

public class Server
{
    public static int PORT = 7800;

    private static byte[] convertImageToByte(String filename) throws IOException
    {
        BufferedImage originalImage = ImageIO.read(new File(filename));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }

    public static void main(String[] args) throws IOException {

        byte[] masImage = convertImageToByte("C:/success/sign.jpg");
        System.out.println(masImage.length);

        ServerSocket socket = new ServerSocket(PORT);//СОЗДАЛИ СОКЕТ СЕРВЕРА

        System.out.println("Waiting connection");
        Socket clientsocket = socket.accept();
        System.out.println("Accept!");

        //create outputstream and inputstream
        ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientsocket.getInputStream());
        try {
            //send size of array
            int NUMBER = masImage.length;
            oos.writeObject(NUMBER);
            oos.flush();

            boolean work = true;
            String flag = "continue";
            String messege = (String) in.readObject();
            System.out.println(messege);

            if (messege.equals("Are you ready?"))
            {
                while (work)
                {
                    oos.writeObject(masImage);
                    work = false;
                }
            }
            else
                {
                  oos.close();
                  in.close();
                }
        } catch (ClassNotFoundException e)
        {e.printStackTrace();}
        System.out.println("Hello,world!");
    }
}
