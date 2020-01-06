package pl.myjava.util.read.htmlpage;

import java.io.IOException;

public interface ReaderStrategy {
	int read() throws IOException;
	boolean canRead();
}