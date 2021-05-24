window.onload = () => {

    const BASE_URL = 'http://localhost:8080/'

    if (/admin\/home/.test(window.location.href)) {
        let button = document.querySelector("button.changes-button")
        button.addEventListener('click', function(event) {
            let idBar = parseInt(document.querySelector('#idbar').value)
            let bar = {
                idBar: parseInt(document.querySelector('#idbar').value),
                name: document.querySelector('#name').innerHTML,
                description: document.querySelector('#description').value,
                address: document.querySelector('#address').value,
                phone: parseInt(document.querySelector('#phone').value),
                schedule: document.querySelector('#schedule').value,
                currentCapacity: parseInt(document.querySelector('#currentCapacity').value),
                totalCapacity: parseInt(document.querySelector('#totalCapacity').value),
                allowedCapacity: parseInt(document.querySelector('#allowedCapacity').value),
                length: parseInt(document.querySelector('#length').value),
                latitude: parseInt(document.querySelector('#latitude').value),
            }
            fetch(BASE_URL + 'api/bars/' + idBar, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(bar),
                })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data, bar);
                    window.location = "/admin/home"
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        })

        let deleteImgButtons = document.querySelectorAll('[id^="delete-img-"]')
        deleteImgButtons.forEach(item => {
            item.addEventListener('click', function(event) {
               
                var idSplited = event.target.id.split("-")
                let idImgBar = idSplited[2]
                 if (!confirm("Estás seguro de que quieres eliminar esta imagen (Esta acción es irreversible)")){
                     return
                 }
                  
                  fetch('/admin/image/delete/' + idImgBar, {
                      method: 'DELETE'
                  })
                  .then(response => response.json())
                  .then(data => {
                      console.log('Success:', data);
                      window.location = "/admin/home"
                      
                  })
                  .catch((error) => {
                      alert("no se ha podido eliminar la imagen")
                      console.error('Error:', error);
                  });
              })
        } )
        
    }
}