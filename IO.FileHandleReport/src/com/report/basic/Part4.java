package com.report.basic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

public class Part4 {

	// everything this demo creates stays inside this folder
	static final Path BASE = Paths.get("D:\\DemoFolder");

	public static void main(String[] args) {
		// validation: the folder has to exist and be writable before anything else runs
		if (!Files.isDirectory(BASE)) {
			try {
				Files.createDirectories(BASE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Cannot create " + BASE + "\n" + e.getMessage());
				return;
			}
		}
		if (!Files.isWritable(BASE)) {
			JOptionPane.showMessageDialog(null, BASE + " is not writable");
			return;
		}

		String[] options = { "1. create directories", "2. list files", "3. delete", "4. attributes", "5. exit" };
		int menu;
		do {
			menu = JOptionPane.showOptionDialog(null, "FILE MANIPULATION\n" + BASE, "File Manipulation",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			switch (menu) {
			case 0:
				createDirs();
				break;
			case 1:
				list();
				break;
			case 2:
				delete();
				break;
			case 3:
				attributes();
				break;
			case 4:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Error 1-5 lang");
			}
		} while (menu != 4);
	}

	private static void createDirs() {
		Path target = BASE.resolve("create/deep/a/b/c");
		StringBuilder sb = new StringBuilder("Inside " + BASE + "\n\n");

		// validation: do not create what is already there
		if (Files.isDirectory(target)) {
			sb.append("already there: ").append(target);
		} else {
			try {
				Files.createDirectories(target);
				sb.append("createDirectories(\"create/deep/a/b/c\") created: ").append(target);
			} catch (IOException e) {
				sb.append("createDirectories failed: ").append(e.getMessage());
			}
		}

		JOptionPane.showMessageDialog(null, sb);

		// dropped: mkdir() and mkdirs() only return true or false with no reason,
		// and Files.createDirectory() throws NoSuchFileException here because
		// its parent folder does not exist yet.
	}

	private static void list() {
		StringBuilder sb = new StringBuilder("Inside " + BASE + "\n\n");

		// validation: nothing to walk if the folder is not there
		if (!Files.isDirectory(BASE)) {
			sb.append("missing: ").append(BASE);
			JOptionPane.showMessageDialog(null, sb);
			return;
		}

		// try-with-resources closes the Stream even when the loop throws
		try (Stream<Path> stream = Files.walk(BASE, 3)) {
			stream.forEach(p -> sb.append("   ").append(p).append('\n'));
		} catch (IOException e) {
			sb.append("walk failed: ").append(e.getMessage());
		}

		JOptionPane.showMessageDialog(null, sb);

		// dropped: File.list() and File.listFiles() hand back arrays that quietly
		// come back null on failure, and Files.list() needs the same
		// try-with-resources that Files.walk() already shows.
	}

	private static void delete() {
		Path target = BASE.resolve("create");
		StringBuilder sb = new StringBuilder("Inside " + BASE + "\n\n");

		// validation: nothing to delete
		if (!Files.exists(target)) {
			sb.append("nothing to delete: create is not there");
		} else {
			try {
				deleteAll(target);
				sb.append("deleteAll(\"create\") removed the whole tree");
			} catch (IOException e) {
				sb.append("deleteAll failed: ").append(e.getMessage());
			}
		}

		JOptionPane.showMessageDialog(null, sb);

		// dropped: File.delete() returns false on a folder that still holds files
		// and never says why. Files.delete() would throw, but a folder can only go
		// after everything inside it is gone, which is what deleteAll() does.
	}

	private static void attributes() {
		Path file = BASE.resolve("report.txt");
		StringBuilder sb = new StringBuilder();

		try {
			Files.writeString(file, "file handle report\n");

			// validation: read the attributes only if it really is a file
			if (!Files.isRegularFile(file)) {
				sb.append("not a file: ").append(file);
			} else {
				BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
				sb.append(file).append("\n\n");
				sb.append("size: ").append(attrs.size()).append(" bytes\n");
				sb.append("last modified: ").append(attrs.lastModifiedTime()).append('\n');
				sb.append("created: ").append(attrs.creationTime()).append('\n');
			}
		} catch (IOException e) {
			sb.append("readAttributes failed: ").append(e.getMessage());
		}

		JOptionPane.showMessageDialog(null, sb);
	}

	// recursive delete: a folder can only go after everything inside it is gone
	private static void deleteAll(Path folder) throws IOException {
		if (!Files.exists(folder)) // validation
			return;

		try (Stream<Path> stream = Files.walk(folder)) { // try-with-resources
			for (Path p : stream.sorted(Comparator.reverseOrder()).toList())
				Files.delete(p); // throws instead of returning false
		}
	}
}
