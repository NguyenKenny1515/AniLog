document.addEventListener("DOMContentLoaded", function () {
    const searchBar = document.getElementById("anime-search-bar");
    const dropdown = document.getElementById("anime-results-dropdown");
    let debounceTimer;

    searchBar.addEventListener("keyup", function (event) {
        clearTimeout(debounceTimer);

        debounceTimer = setTimeout(() => {
            searchAnime();
        }, 500);

        if (event.key === "Enter" && searchBar.value.trim().length > 0) {
            window.location.href = `/anime/search?q=${encodeURIComponent(searchBar.value.trim())}`;
        }
    });

    searchBar.addEventListener("focus", function () {
        if (dropdown.children.length > 0) {
            dropdown.style.display = "block";
        }
    });

    document.addEventListener("click", function (event) {
        if (!searchBar.contains(event.target) && !dropdown.contains(event.target)) {
            dropdown.style.display = "none";
        }
    });
});

function searchAnime() {
    const query = document.getElementById('anime-search-bar').value.trim();
    const dropdown = document.getElementById('anime-results-dropdown');

    dropdown.innerHTML = '';

    if (query.length < 2) {
        dropdown.style.display = "none";
        return;
    }

    fetch(`/anime/search/api?q=${query}`)
        .then(response => response.json())
        .then(results => {
            if (results.length === 0) {
                dropdown.style.display = "none";
                return;
            }

            results.forEach(anime => {
                const resultItem = document.createElement('a');
                resultItem.href = `/anime/${anime.mal_id}`;
                resultItem.classList.add('list-group-item', 'list-group-item-action', 'd-flex', 'align-items-center');
                resultItem.style.position = "relative";

                const image = document.createElement('img');
                image.src = anime.images.webp.large_image_url;
                image.alt = anime.title;
                image.classList.add('img-fluid', 'me-3');
                image.style.width = '50px';

                resultItem.addEventListener("mouseenter", () => {
                    image.style.transform = "scale(1.5)";
                });

                resultItem.addEventListener("mouseleave", () => {
                    image.style.transform = "scale(1)";
                });

                const textContainer = document.createElement('div');
                textContainer.classList.add('flex-grow-1');

                const title = document.createElement('strong');
                title.textContent = anime.title;

                const typeAndYear = document.createElement('div');
                typeAndYear.classList.add('text-muted', 'small', 'mt-1');
                typeAndYear.textContent = `(${anime.type}, ${anime.year || anime.aired.string.match(/\d{4}/)[0]})`;

                const extraInfo = document.createElement('div');
                extraInfo.classList.add('text-muted', 'small', 'mt-1', 'anime-extra-info');
                extraInfo.style.display = "none";
                extraInfo.innerHTML = `
                    <p class="mb-0">Aired: ${anime.aired.string}</p>
                    <p class="mb-0">Score: ${anime.score || "N/A"}</p>
                    <p class="mb-0">Status: ${anime.status}</p>
                `;

                resultItem.addEventListener("mouseenter", () => {
                    extraInfo.style.display = "block";
                });

                resultItem.addEventListener("mouseleave", () => {
                    extraInfo.style.display = "none";
                });

                textContainer.appendChild(title);
                textContainer.appendChild(typeAndYear);
                textContainer.appendChild(extraInfo);

                resultItem.appendChild(image);
                resultItem.appendChild(textContainer);
                dropdown.appendChild(resultItem);
            });

            const viewAllItem = document.createElement('a');
            viewAllItem.href = `/anime/search?q=${query}`;
            viewAllItem.classList.add('list-group-item', 'list-group-item-action', 'text-center', 'fw-bold');
            viewAllItem.textContent = "View All Results";

            dropdown.appendChild(viewAllItem);
            dropdown.style.display = "block";
        })
        .catch(error => console.error("Error:", error));
}
