/**
 * 
 */

console.log("script are starting....");

const bookReads = document.querySelectorAll(".book-pointer");

console.log(bookReads);

// for more book view
const viewMore = document.getElementById("viewMoreBook");
const moreBook = document.querySelector(".moreBook");

for(let i = 6; i<bookReads.length; i++ ){
	console.log("it working book " + i + "added display-none class..");
	bookReads[i].classList.add("display-none");
}

document.addEventListener("DOMContentLoaded" , () => { 
	if(bookReads.length <= 6 ){
		viewMore.parentElement.classList.add("display-none");
	}
	
});

viewMore.addEventListener("click", () => {
	for(let i = 6; i<bookReads.length; i++ ){
		console.log("it working book " + i + "added display-none class..");
		bookReads[i].classList.toggle("display-none");
	}
	// moreBook.classList.toggle("display-none");
	if (viewMore.innerText == "View More") {
		viewMore.innerHTML = "View Less";
		// console.log(viewMore.innerHTML);
	}
	else {
		viewMore.innerHTML = "View More";
	}
});

console.log("script are endding....");