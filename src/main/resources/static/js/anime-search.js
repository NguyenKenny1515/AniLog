let debounceTimer = null;

function searchAnime() {
    clearTimeout(debounceTimer);

    debounceTimer = setTimeout(() => {
        let query = document.getElementById("anime-search-bar").value;
        let dropdown = document.getElementById("anime-results-dropdown");

        if (query.length < 2) {
            dropdown.style.display = "none";
            return;
        }

        fetch(`/anime/search/api?q=` + encodeURIComponent(query))
            .then(response => response.json())
            .then(data => {
                dropdown.innerHTML = "";

                let maxResults = 5;
                let results = data.slice(0, maxResults);

                results.forEach(anime => {
                    let item = document.createElement("div");
                    item.classList.add("dropdown-item");

                    let img = document.createElement("img");
                    img.src = anime.images.webp.small_image_url;
                    img.alt = `Picture of ${anime.title}`;
                    img.classList.add("result-img");

                    let textContainer = document.createElement("div");
                    textContainer.classList.add("result-text");

                    let title = document.createElement("div");
                    title.classList.add("result-title");
                    title.innerText = anime.title;

                    let typeAndYear = document.createElement("div");
                    typeAndYear.classList.add("result-type-year");
                    typeAndYear.innerText = `(${anime.type}, ${anime.year || anime.aired.string.match(/\d{4}/)[0]})`;

                    let details = document.createElement("div");
                    details.classList.add("result-details");
                    details.innerHTML = `
                        <div><strong>Score:</strong> ${anime.score || "N/A"}</div>
                        <div><strong>Status:</strong> ${anime.status}</div>
                    `;

                    textContainer.appendChild(title);
                    textContainer.appendChild(typeAndYear);
                    textContainer.appendChild(details);
                    item.appendChild(img);
                    item.appendChild(textContainer);
                    item.onclick = () => window.location.href = `/details?id=${anime.id}`;

                    dropdown.appendChild(item);
                });

                // Add "View All Results" link
                if (data.length > maxResults) {
                    let viewAllItem = document.createElement("div");
                    viewAllItem.classList.add("dropdown-item", "view-all");
                    viewAllItem.innerHTML = `<a href="/anime/search?q=${encodeURIComponent(query)}">View All Results</a>`;
                    dropdown.appendChild(viewAllItem);
                }

                dropdown.style.display = results.length > 0 ? "block" : "none";
            })
            .catch(error => console.error("Error fetching search results:", error));
    }, 300);
}

function enterPressedSearch() {
    let query = document.getElementById("anime-search-bar").value.trim();
    if (query.length === 0) return;

    // Redirect to search results page
    window.location.href = `/anime/search?q=${encodeURIComponent(query)}`;
}

// Hide dropdown when clicking outside
document.addEventListener("click", function(event) {
    let dropdown = document.getElementById("anime-results-dropdown");
    if (!document.getElementById("anime-search-bar").contains(event.target)) {
        dropdown.style.display = "none";
    }
});

document.getElementById("anime-search-bar").addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        enterPressedSearch();
    }
});
