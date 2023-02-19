package com.store.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import net.lingala.zip4j.ZipFile;


public class zipFile {
	
	

    public static void main(String[] args) throws IOException {

        Path source = Paths.get("D:\\zip files\\harsha.zip");
        Path target = Paths.get("D:\\zip files\\");
        unzipFolderZip4j(source, target);
//        zipFile(target);
       
    }
    @SuppressWarnings("resource")
	public static void unzipFolderZip4j(Path source, Path target) throws IOException {

            new ZipFile(source.toFile())
                    .extractAll(target.toString());
            


      }
    
    @SuppressWarnings("resource")
	public static void zipFile(Path target) throws IOException {

        // zip file with a single file
//        new ZipFile("harsha.zip").addFile("Testing.txt");

        // zip file with multiple files
        List<File> files = Arrays.asList(
                new File("file1.txt"), new File("file2.txt"));
        new ZipFile("filename.zip").addFiles(files);

        // zip file with a folder
//        new ZipFile("filename.zip").addFolder(new File(target));
    }
}


