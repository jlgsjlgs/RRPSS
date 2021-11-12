import java.util.*;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.InputStream;

public class ReadFile {
	private String fileName;
	private InputStream is;
	ReadFile(String fileName){
		this.fileName = fileName;
		is = this.getClass().getResourceAsStream(this.fileName);
	}
	
	String getfileName() {
		return fileName;
	}

	InputStream getIS() {
		return is;
	}
}
