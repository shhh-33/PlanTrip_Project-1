$planList = {
    copyJoinTipLink: function (tripId) {
        var url = "http:localhost:8080/joinTrip?tripId=" + tripId;
        const element = document.getElementById('copied_textarea');
        element.style.display='block';
        element.innerText = url
        element.focus();
        element.select();
        document.execCommand('copy');
        element.style.display='none';
        alert("Copied!");
    }
}