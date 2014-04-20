package testtool.models.questiondb;

/**
 * Question type corresponding to a Coding question
 * Students will be asked to fill in some code which will then
 * be taken and fed through STDIN to the instructor defined script
 * which will run and output a number from 0-100
 *
 * @author RJ Almada (rjalmada@calpoly.edu)
 * @version 14apr14
 *
 */
public class CodeQuestion extends Question {
	/**
	 * The path to where the script to check student answers
	 * against is stored
	 */
	String scriptPath;
}