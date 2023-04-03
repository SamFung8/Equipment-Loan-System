import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.lang.model.util.ElementScanner6;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(System.in);

		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();

		Scanner inFile = new Scanner(new File(filepathname));

		// The first command in the file must be to set the system date
		// (eg. "startNewDay 03-Jan-2021"); and it cannot be undone
		String cmdLine1 = inFile.nextLine();
		String[] cmdLine1Parts = cmdLine1.split(" ");
		System.out.println("\n> " + cmdLine1);
		SystemDate.createTheInstance(cmdLine1Parts[1]);

		while (inFile.hasNext()) {
			String cmdLine = inFile.nextLine().trim();

			// Blank lines exist in data file as separators. Skip them.
			if (cmdLine.equals(""))
				continue;

			System.out.println("\n> " + cmdLine);

			// split the words in actionLine => create an array of word strings
			String[] cmdParts = cmdLine.split(" ");

			try {
				if (cmdParts[0].equals("register")) {
					if (cmdParts.length != 3)
						throw new ExInsufficientCommand();
					else
						(new CmdRegister()).execute(cmdParts);
				} else if (cmdParts[0].equals("listMembers")) {
					(new CmdListMembers()).execute(cmdParts);
				} else if (cmdParts[0].equals("listItems")) {
					(new CmdListItems()).execute(cmdParts);
				} else if (cmdParts[0].equals("startNewDay")) {
					(new CmdStartNewDay()).execute(cmdParts);
				} else if ((cmdParts[0].equals("arrive"))) {
					if (cmdParts.length != 3)
						throw new ExInsufficientCommand();
					else
						(new CmdArrive()).execute(cmdParts);
				} else if ((cmdParts[0].equals("checkout"))) {
					if (cmdParts.length != 3)
						throw new ExInsufficientCommand();
					else
						(new CmdCheckout()).execute(cmdParts);
				} else if ((cmdParts[0].equals("checkin"))) {
					if (cmdParts.length != 3)
						throw new ExInsufficientCommand();
					else
						(new CmdCheckin()).execute(cmdParts);
				} else if ((cmdParts[0].equals("request"))) {
					if (cmdParts.length != 3)
						throw new ExInsufficientCommand();
					else
						(new CmdRequest()).execute(cmdParts);
				} else if ((cmdParts[0].equals("cancelRequest"))) {
					if (cmdParts.length != 3)
						throw new ExInsufficientCommand();
					else
						(new CmdCancelRequest()).execute(cmdParts);
				} else if (cmdParts[0].equals("undo")) {
					RecordedCommand.undoOneCommand();
				} else if (cmdParts[0].equals("redo")) {
					RecordedCommand.redoOneCommand();
				} else {
					throw new ExUnknowCommand();
				}
			} catch (ExUnknowCommand e) {
				System.out.println(e.getMessage());
			} catch (ExInsufficientCommand e) {
				System.out.println(e.getMessage());
			}
		}
		inFile.close();
		in.close();
	}
}
