package pdf;

import java.awt.Image;  
import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
import java.lang.reflect.Method;  
import java.nio.MappedByteBuffer;  
import java.nio.channels.FileChannel;  
import java.security.AccessController;  
import java.security.PrivilegedAction;  
  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGEncodeParam;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
import com.sun.pdfview.PDFFile;  
import com.sun.pdfview.PDFPage;  
  
public class PDF2JPG {  
    public static void main(String[] args){  
        PDF2JPG.changePdfToImg();  
    }  
  
    private static void changePdfToImg() {  
  
        try {  
        File file = new File("D:/workspace/DocumentOnlineView/WebContent/resource/guideRegExp.pdf");  
        RandomAccessFile raf = new RandomAccessFile(file, "r");  
        FileChannel channel = raf.getChannel();  
        MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());  
        PDFFile pdffile = new PDFFile(buf);  
        for (int i = 1; i <= pdffile.getNumPages(); i++) {  
        PDFPage page = pdffile.getPage(i);  
        Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox().getWidth()), ((int) page.getBBox().getHeight()));  
        Image img = page.getImage(rect.width, rect.height, rect,  
        null, // null for the ImageObserver  
        true, // fill background with white  
        true // block until drawing is done  
        );  
        BufferedImage tag = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);  
        tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height, null);  
//      File imgfile = new File("D://work//mybook//FilesNew//img//" + i + ".jpg");  
//      if(imgfile.exists()){  
//          if(imgfile.createNewFile())  
//          {  
//              System.out.println("创建图片："+"D://work//mybook//FilesNew//img//" + i + ".jpg");  
//          } else {  
//              System.out.println("创建图片失败！");  
//          }  
//      }  
        
        File dir = new File("D:/img");
        if(!dir.exists()){
        	dir.mkdir();
        }
        FileOutputStream out = new FileOutputStream("D:/img/" + i + ".jpg"); // 输出到文件流  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);  
        param2.setQuality(1f, false);// 1f是提高生成的图片质量  
        encoder.setJPEGEncodeParam(param2);  
        encoder.encode(tag); // JPEG编码  
        out.close();  
        }  
        channel.close();  
        raf.close();  
        unmap(buf);//如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法  
        } catch (FileNotFoundException e) {  
        e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
          
    }  
  
    private static void unmap(final Object buffer) {  
        // TODO Auto-generated method stub  
  
        AccessController.doPrivileged(new PrivilegedAction() {  
        public Object run() {  
        try {  
        Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);  
        getCleanerMethod.setAccessible(true);  
        sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);  
        cleaner.clean();  
        } catch (Exception e) {  
        e.printStackTrace();  
        }  
        return null;  
        }  
        });  
    }  
      
}  