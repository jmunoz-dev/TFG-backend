window.onload = () => {

    const BASE_URL = 'http://localhost:8080/'

    if (/admin\/home/.test(window.location.href)) {
        let button = document.querySelector("button.changes-button")
        button.addEventListener('click', function(event) {
            let bar = {
                idBar: document.querySelector('#idbar').value,
                name: document.querySelector('#name').innerHTML,
                description: document.querySelector('#description').value,
                address: document.querySelector('#address').value,
                phone: document.querySelector('#phone').value,
                schedule: document.querySelector('#schedule').value,
                currentCapacity: document.querySelector('#currentCapacity').value,
                totalCapacity: document.querySelector('#totalCapacity').value,
                allowedCapacity: document.querySelector('#allowedCapacity').value,
                length: document.querySelector('#length').value,
                latitude: document.querySelector('#latitude').value,
            }
            fetch(BASE_URL + '/api/bars/' + idbar, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(bar),
                })
                .then(response => console.log(response))
                .then(data => {
                    console.log('Success:', data, bar);
                    if (data === true)
                        window.location = "/admin/home"
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        })
    }
}