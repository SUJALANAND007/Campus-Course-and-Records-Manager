package edu.ccrm.cli;
public class Main {
public static void main(String[] args) {
assert args != null : "Args should not be null";
CliManager cliManager = new CliManager();
cliManager.run();
}
}