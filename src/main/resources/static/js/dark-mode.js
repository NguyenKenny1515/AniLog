document.addEventListener("DOMContentLoaded", function () {
    const toggleButton = document.getElementById("toggle-dark-mode");
    const body = document.body;

    // Load saved theme from local storage
    if (localStorage.getItem("darkMode") === "enabled") {
        body.classList.add("dark-mode");
        toggleButton.textContent = "☀️ Light Mode";
    }

    toggleButton.addEventListener("click", function () {
        body.classList.toggle("dark-mode");

        // Save user preference in localStorage
        if (body.classList.contains("dark-mode")) {
            localStorage.setItem("darkMode", "enabled");
            toggleButton.textContent = "☀️ Light Mode";
        } else {
            localStorage.setItem("darkMode", "disabled");
            toggleButton.textContent = "🌙 Dark Mode";
        }
    });
});