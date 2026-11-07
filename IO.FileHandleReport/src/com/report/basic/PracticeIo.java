package com.report.basic;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class PracticeIo {

	static final File BASE = new File("D://DemoFolder");

	public static void main(String[] args) {
		// IO: mkdir() returns false if parent missing, no reason given
		if (!BASE.isDirectory()) {
			if (!BASE.mkdirs()) {
				JOptionPane.showMessageDialog(null, "cannot create " + BASE);
				return;
			}
		}

		String[] options = {
				"1. Create Directories",
				"2. List files",
				"3. Delete files",
				"4. Attributes",
				"5. Exit"
		};
		int menu;

		do {
			menu = JOptionPane.showOptionDialog(null,
					"File manipulation options (IO)",
					"hi", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					options,
					options[0]);

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
				JOptionPane.showMessageDialog(null, "Error, 1-5 lang");
			}
		} while (menu != 4);
	}

	private static void createDirs() {
		File target = new File(BASE, "alphabet/a/b/c/d/e/g");
		StringBuilder sb = new StringBuilder();

		if (target.isDirectory()) {
			sb.append("target already exists: ").append(target);
		} else {
			// IO: mkdirs() creates all levels, returns true/false, no reason
			if (target.mkdirs()) {
				sb.append("Successfully created: ").append(target);
			} else {
				sb.append("mkdirs() failed: ").append(target);
			}
		}

		JOptionPane.showMessageDialog(null, sb);
	}

	private static void list() {
		File target = new File(BASE, "alphabet");
		StringBuilder sb = new StringBuilder();

		if (!target.isDirectory()) {
			sb.append("no dirs in: ").append(BASE);
			JOptionPane.showMessageDialog(null, sb);
			return;
		}

		// IO: list() returns String[] of names, null if folder missing
		String[] names = target.list();
		if (names == null) {
			sb.append("list() returned null");
		} else {
			for (String name : names)
				sb.append(name).append("\n");
		}

		// IO: listFiles() returns File[] with full info
		File[] files = target.listFiles();
		if (files != null) {
			for (File f : files)
				sb.append(f.getName()).append(f.isDirectory() ? "/" : " (" + f.length() + " bytes)").append("\n");
		}

		JOptionPane.showMessageDialog(null, sb);
	}

	private static void delete() {
		File target = new File(BASE, "alphabet");
		StringBuilder sb = new StringBuilder();

		if (!target.exists()) {
			sb.append("nothing to delete");
		} else {
			// IO: delete() returns false on non-empty folder, no reason given
			if (deleteAll(target)) {
				sb.append("deleted the entire tree");
			} else {
				sb.append("failed to delete all");
			}
		}

		JOptionPane.showMessageDialog(null, sb);
	}

	private static boolean deleteAll(File folder) {
		if (!folder.exists())
			return true;

		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteAll(f);
				} else {
					f.delete(); // returns false if locked, no reason
				}
			}
		}
		return folder.delete(); // returns false if not empty, no reason
	}

	private static void attributes() {
		File file = new File(BASE, "report.txt");
		StringBuilder sb = new StringBuilder();

		try {
			// IO has no Files.writeString, use manual write
			java.io.FileWriter writer = new java.io.FileWriter(file);
			writer.write("file handle report\n");
			writer.close();

			if (!file.isFile()) {
				sb.append("not a file: ").append(file);
			} else {
				// IO: length() and lastModified() only, no creation time
				sb.append(file).append("\n\n");
				sb.append("size: ").append(file.length()).append(" bytes\n");
				sb.append("last modified: ").append(file.lastModified()).append(" ms since epoch\n");
				sb.append("created: NOT AVAILABLE in IO\n");
			}
		} catch (IOException e) {
			sb.append("failed: ").append(e.getMessage());
		}

		JOptionPane.showMessageDialog(null, sb);
	}
}
