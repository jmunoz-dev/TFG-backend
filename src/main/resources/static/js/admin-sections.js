window.onload = () => {
    if (/admin\/home/.test(window.location.href)) {
        let button = document.querySelector("button.changes-button")
        button.addEventListener('click', function(event) {
            let bar = {
                idbar: document.querySelector('#idbar'),
                description: document.querySelector('#description'),
                address: document.querySelector('#address'),
            }
            alert(bar)
                // fetch('/api/bars/', {
                //         method: 'PUT',
                //         headers: {
                //             'Content-Type': 'application/json',
                //         },
                //         body: JSON.stringify(bar),
                //     })
                //     .then(response => response.json())
                //     .then(data => {
                //         console.log('Success:', data, bar);
                //         if (data === true)
                //             window.location = "/admin/home"
                //     })
                //     .catch((error) => {
                //         console.error('Error:', error);
                //     });
        })
    }
}