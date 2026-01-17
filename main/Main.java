package main;

import ui.TopMain;
import controller.TaskManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TaskManager todo = new TaskManager();
        new TopMain(todo).setVisible(true);
	
	}

}