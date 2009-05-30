import com.thoughtworks.selenium.*;

before "start selenium", {
 given "selenium is up and running", {
  selenium = new DefaultSelenium("localhost",
   4444, "*firefox", "http://www.google.com")
  selenium.start()
 }
}

scenario "Execute google search", {

 when "filling out the google search text box", {
  selenium.open("http://www.google.com/webhp?hl=en")
  selenium.type("q", "hello world")
 }
 
 and "the search button has been clicked", {
  selenium.click("btnG")
 }

 then "the title of search result should have hello world - Google Search", {
    selenium.waitForPageToLoad("5000")
    selenium.getTitle().shouldBeEqualTo "hello world - Google Search"
 }

}

after "stop selenium", {
 then "selenium should be shutdown", {
  selenium.stop()
 }
}
