window.onload = () => {

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
            fetch('/api/bars/' + idBar, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(bar),
                })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data);
                    window.location = "/admin/home"
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        })
    }

    if (/admin\/offers/.test(window.location.href)) {

        let modal_popup_Code =
            `<div class="modal hidden" id="modal-one">
            <div class="modal-container">
                <h1>Activar código</h1>
                <div class="modal-body">
                    <input id="code-activation" class="code-activation" name="code-activation" />
                    <button id="modal-submit" class="modal-submit">Comprobar y activar</button>
                </div>        
            <button id="close-modal" class="modal-close modal-exit">X</button>
            </div>
            </div>`;
        document.querySelector('body').insertAdjacentHTML('beforeend', modal_popup_Code)

        let buttonCorrect = `<div class="modal-correct-close" onclick="closeSuccess()">Cerrar</div>`

        let buttonShowModal = document.querySelector("button#activation")
        let buttonActivate = document.querySelector("button#modal-submit")
        let buttonCloseModal = document.querySelector("button#close-modal")

        let buttonCreateNew = document.querySelector("button#creation")


        buttonShowModal.addEventListener('click', function(event) {
            document.querySelector('#modal-one').classList.toggle('hidden')
            document.querySelector('#modal-one').classList.toggle('shown')
            if (document.querySelector("div.codeErrormsg") != null) {
                document.querySelector("div.codeErrormsg").remove()
            }
        })

        buttonActivate.addEventListener('click', function(event) {
            let codeValue = document.querySelector("input#code-activation")
            let codeErrorOffer = `<div class="codeErrormsg"><p>El código no es correcto</p></div>`
            let codeSuccessOffer = `<div class="codeSuccessmsg"><p>Activado correctamente</p></div>`
            let codeAlertOffer = `<div class="codeAlertOffer"><p>La oferta no es correcta o ya se ha activado</p></div>`

            fetch('/api/userOffers/' + codeValue.value, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                })
                .then(response => response.json())
                .then(data => {
                    if (data.status === 404) {
                        if (document.querySelector("div.codeErrormsg") != null) {
                            document.querySelector("div.codeErrormsg").remove()
                        }
                        codeValue.insertAdjacentHTML('beforebegin', codeErrorOffer)

                    }
                    if (data.status === 200) {
                        if (document.querySelector("div.codeErrormsg") != null) {
                            document.querySelector("div.codeErrormsg").remove()
                        }
                        codeValue.insertAdjacentHTML('beforebegin', data.offerTitle)
                        codeValue.insertAdjacentHTML('beforebegin', data.offerPrice)
                        codeValue.insertAdjacentHTML('beforebegin', codeSuccessOffer)
                        buttonActivate.style.display = "none"
                        buttonActivate.insertAdjacentHTML('beforebegin', buttonCorrect)

                    }
                    if (data === 405) {

                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('Algo ha salido mal')
                });


        })

        buttonCloseModal.addEventListener('click', function(e) {
            document.querySelector('#modal-one').classList.toggle('hidden')
            document.querySelector('#modal-one').classList.toggle('shown')
        })





    }
}

function deleteOffer(id) {
    allowDelete = window.confirm('¿Estás seguro que deseas eliminar esta oferta?')
    if (allowDelete) {
        fetch('/api/offers/' + id, {
                method: 'DELETE',
            })
            .then(res => res.json())
            .then(res => console.log(res))
    }
}

function editOffer(id) {

    let data = retrieveOffer(id)




}

function retrieveOffer(id) {
    let offer = null
    let newOffer = null;

    fetch('api/offers/' + id)
        .then(function(response) {
            offer = response.json();
        })
        .catch(function(err) {
            console.error(err);
        });

}

function closeSuccess() {
    document.querySelector('#modal-one').innerHTML(modal_popup)
}