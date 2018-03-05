import java.util.ArrayList;

public class Font {
	private static int offSet = -5;
	private static ArrayList<Sprite> Text;
	
	public static void Font(){
		
	}
	
	public static ArrayList<Sprite> getTextures(int[] alphabet, String text, int x, int y, int[] spriteSize){
		Text = new ArrayList<Sprite>();
		for (int i = 0; i < text.length(); i++){
    		switch (text.charAt(i)){
    			case 'A': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[0]));
    				break;
    			case 'B': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[1])); 
					break;
    			case 'C': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[2]));
					break;
    			case 'D': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[3]));
					break;
    			case 'E': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[4]));
					break;
    			case 'F': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[5]));
					break;
    			case 'G': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[6]));
					break;
    			case 'H': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[7]));
					break;
    			case 'I': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[8]));
					break;
    			case 'J': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[9]));
					break;
    			case 'K': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[10]));
					break;
    			case 'L': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[11]));
					break;
    			case 'M': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[12]));
					break;
    			case 'N': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[13]));
					break;
    			case 'O': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[14]));
					break;
    			case 'P': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[15]));
					break;
    			case 'Q': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[16]));
					break;
    			case 'R': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[17])); 
					break;
    			case 'S': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[18]));
					break;
    			case 'T': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[19]));
    				break;
    			case 'U': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[20]));
					break;
    			case 'V': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[21]));
					break;
    			case 'W': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[22]));
					break;
    			case 'X': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[23]));
					break;
    			case 'Y': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[24]));
					break;
    			case 'Z': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[25]));
					break;
    			case '0': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[26]));
					break;
    			case '1': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[27]));
					break;
    			case '2': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[28]));
					break;
    			case '3': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[29]));
					break;
    			case '4': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[30]));
					break;
    			case '5': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[31]));
					break;
    			case '6': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[32]));
					break;
    			case '7': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[33]));
					break;
    			case '8': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[34]));
					break;
    			case '9': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[35]));
					break;
    			case ':': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[36]));
					break;
    			case '!': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[37]));
					break;
    			case '?': Text.add(new Sprite(x + ((spriteSize[0] - offSet) * i), y, spriteSize, alphabet[38]));
					break;
				default: break;
    		}
    	}
		return(Text);
	}

}
