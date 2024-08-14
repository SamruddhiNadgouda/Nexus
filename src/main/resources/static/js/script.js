console.log("Script loaded!");

let currTheme = getTheme();
//initially run 
document.addEventListener("DOMContentLoaded", () =>{
    changeTheme();
});

function changeTheme() {
    //set to web page
    changePageTheme(currTheme, currTheme);
    //set the listener to change theme button
    const changeTheme = document.querySelector("#theme_change");
    const oldTheme = currTheme;
    changeTheme.addEventListener("click", (e) =>{
        // console.log("Theme button clicked!")
        const oldTheme = currTheme;
        if(currTheme == "dark"){
            //theme to light
            currTheme= "light";
        }else{
            //theme to dark
            currTheme="dark";
        }
        changePageTheme(currTheme, oldTheme);
    });
}

//set theme to localstorage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

//get theme from localstorage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

//change current page theme
function changePageTheme(theme, oldTheme){
    //localstorage need to update
    setTheme(currTheme);
    //remove the current theme
    document.querySelector('html').classList.remove(oldTheme);
    //set the current theme
    document.querySelector('html').classList.add(theme);
    //change the text of button
    document
    .querySelector("#theme_change")
    .querySelector('span').textContent = theme == 'light' ? 'Dark' : 'Light';
}

