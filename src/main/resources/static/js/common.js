document.addEventListener("DOMContentLoaded", function () {
    fetch("/v1/user")
        .then(responce => responce.text())
        .then(data => {
            document.getElementById("userNameText").innerText = data;
        })
});