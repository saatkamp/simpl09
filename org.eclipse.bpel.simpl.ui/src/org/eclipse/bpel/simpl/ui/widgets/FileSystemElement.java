package org.eclipse.bpel.simpl.ui.widgets;


public class FileSystemElement {

	String commandName;
	String commandDescription;
	
	public String getCommandDescription() {
		return commandDescription;
	}
	public void setCommandDescription(String commandDescription) {
		this.commandDescription = commandDescription;
	}
	public String getDrive_info() {
		return drive_info;
	}
	public void setDrive_info(String driveInfo) {
		drive_info = driveInfo;
	}


	String driveName;
	String drive_info;
	
	
	String folderName;
	String fileName;
	
	
	
	
	boolean isFolder=false;
	boolean isFile=false;
	boolean isCommand=false;
	boolean isDrive=false;
	
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String listOfCommands) {
		this.commandName = listOfCommands;
	}
	public String getDriveName() {
		return driveName;
	}
	public void setDriveName(String drive) {
		this.driveName = drive;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folder) {
		this.folderName = folder;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String file) {
		this.fileName = file;
	}
	public boolean isFolder() {
		return isFolder;
	}
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	public boolean isFile() {
		return isFile;
	}
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	public boolean isCommand() {
		return isCommand;
	}
	public void setCommand(boolean isCommand) {
		this.isCommand = isCommand;
	}
	public boolean isDrive() {
		return isDrive;
	}
	public void setDrive(boolean isDrive) {
		this.isDrive = isDrive;
	}
	
	
	public FileSystemElement(){
		
	}
}
