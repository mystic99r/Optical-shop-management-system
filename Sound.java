import java.io.*;
import javax.sound.sampled.*;
import javax.sound.midi.*;

public class Sound
{
		Sequence sequence;
 		Sequencer sequencer;
		Clip clip;
  	
		public Sound(String file)
		{
			File f=new File(file);
			AudioInputStream ain;
			try {
			ain  = AudioSystem.getAudioInputStream(f);
			DataLine.Info info =
			new DataLine.Info(Clip.class,ain.getFormat( ));
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ain);
            		}
			catch(Exception e)
			{
			System.out.println(e);	
			}			
            		clip.start( );			
		}
}
