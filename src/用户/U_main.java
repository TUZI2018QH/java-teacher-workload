package 用户;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class U_main extends Thread
{
	private Player player;
	//private File music;
	
	public U_main() {super();}
	
//	public U_main(File file)
//	{
//		this.music = file;
//	}
	public void run() 
	{
		super.run();
		try 
		{
			paly();
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JavaLayerException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paly() throws FileNotFoundException, JavaLayerException
	{
	    BufferedInputStream buffer =
                new BufferedInputStream(new FileInputStream("mic\\main_back_music.mp3"));
	    player = new Player(buffer);
	    player.play();
	}
	
	public void UU_load()
	{
		new UU_load();
	}
	
	public static void main(String[] args) 
	{
//		new U_load();
		U_main u = new U_main();
		          
		try 
		{
			u.UU_load();
			u.paly();
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (JavaLayerException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
