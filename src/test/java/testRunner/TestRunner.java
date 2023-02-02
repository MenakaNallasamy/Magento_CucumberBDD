package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {".//Features/"},//(.//-current prjt  execute all feature file
		//features= {".//Features/Signin.feature"},
		//features= {".//Features/SigninDDT.feature"},
		//features= {".//Features/SigninDDTExcel.feature"},
		//features="@target/rerun.txt",   // Runs only failures
		   		
		
		glue="stepDefinitions", //glue is a keyword, specify steps-package name
		plugin= {"pretty",
				"html:reports/myreport.html",
				"json:reports/myreport.json",
				"rerun:target/rerun.txt"},    //Mandatory to capture failures
		dryRun=false,//check whether in featurefile steps corressponding stepdefinition is available r nt
		monochrome=true, //removing unnecessory r junk character in console window 
		//tags="@sanity"  //scenarios tagged with @sanity
		// tags="@regression"  //scenarios tagged with @regression
		//tags="@sanity and @regression"  //scenarios tagged with both @sanity @regression
	    tags="@sanity or @regression"  //scenarios tagged with either @sanity or @regression
		//tags="@sanity and not regression", //scenarios tagged with @sanity but not @reggresion 
		
		)
public class TestRunner {
	
}
