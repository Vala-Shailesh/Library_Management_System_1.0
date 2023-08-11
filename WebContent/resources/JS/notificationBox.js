console.log("Scrip are starting........");

// registration popup 
const registrationBox = document.querySelectorAll(".register-box");
const registrationBoxClsBtn = document.querySelectorAll(".registerBox-close-btn");

for( const item of registrationBoxClsBtn){
	item.addEventListener("click", () => {
		item.parentNode.style.display = "none";
	});
}

console.log("Scrip are ending........");