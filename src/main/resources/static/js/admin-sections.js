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
        document.querySelector("#upload").addEventListener("click", (e) => {
            var maxSize = 1048575;
            if (!document.querySelector("#image").files[0])
                return

            if (document.querySelector("#image").files[0].size > maxSize) {
                e.preventDefault();
                alert(`Archivo demasiado grande (el archivo no puede ser mayor a ${Math.floor( maxSize/1e6)} Mb)`)
            }

        })
        let deleteImgButtons = document.querySelectorAll('[id^="delete-img-"]')
        deleteImgButtons.forEach(item => {
            item.addEventListener('click', function(event) {

                var idSplited = event.target.id.split("-")
                let idImgBar = idSplited[2]
                if (!confirm("Estás seguro de que quieres eliminar esta imagen (Esta acción es irreversible)")) {
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
        })

    }

    if (/admin\/offers/.test(window.location.href)) {

        let modal_popup_Code =
            `<div class="modal hidden" id="modal-one">
            <div class="modal-container">
                <h1 id="title-popup">Activar código</h1>
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

        let buttonCreateNew = document.querySelector("button#creationOffer")

        if (buttonCreateNew != null) {
            buttonCreateNew.addEventListener('click', function(e) {
                let idbar = parseInt(document.querySelector('#idbar').value)
                let offer = {
                    bar: {
                        idbar: idbar
                    },
                    idOffer: 0,
                    offerTitle: document.querySelector('input[name="offerTitle"').value,
                    offerDescription: document.querySelector('input[name="offerDescription"').value,
                    offerPrice: document.querySelector('input[name="offerPrice"').value,
                    offerMinimunPoints: parseInt(document.querySelector('input[name="offerMinimunPoints"').value),
                    offerRewardsPoints: parseInt(document.querySelector('input[name="offerRewardsPoints"').value),
                    startDate: document.querySelector('input[name="startDate"').value,
                    endDate: document.querySelector('input[name="endDate"').value,
                }
                fetch('/api/offers/', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(offer),
                    })
                    .then(response => response.text())
                    .then(data => {
                        console.log('Success');
                        window.location = "/admin/offers"
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            })
        }

        if (buttonShowModal != null) {
            buttonShowModal.addEventListener('click', function(event) {
                document.querySelector('#modal-one').classList.toggle('hidden')
                document.querySelector('#modal-one').classList.toggle('shown')
                if (document.querySelector("div.codeErrormsg") != null) {
                    document.querySelector("div.codeErrormsg").remove()
                }
            })
        }

        buttonActivate.addEventListener('click', function(event) {
            let codeValue = document.querySelector("input#code-activation")
            let codeErrorOffer = `<div class="codeErrormsg"><p>El código no es correcto</p></div>`
            let codeSuccessOffer = `<div class="codeSuccessmsg"><p>Activado correctamente</p></div>`
            let codeAlertOffer = `<div class="codeAlertOffer"><p>La oferta ya se ha activado</p></div>`

            fetch('/api/userOffers/' + codeValue.value, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                })
                .then(response => response.json())
                .then(data => {
                    if (data.status === 404 || data.status === 405) {
                        if (document.querySelector("div.codeErrormsg") != null) {
                            document.querySelector("div.codeErrormsg").remove()
                        }
                        if (document.querySelector("div.codeAlertOffer") != null) {
                            document.querySelector("div.codeAlertOffer").remove()
                        }
                        codeValue.insertAdjacentHTML('beforebegin', codeErrorOffer)

                    } else if (data.status === 409) {
                        if (document.querySelector("div.codeErrormsg") != null) {
                            document.querySelector("div.codeErrormsg").remove()
                        }
                        if (document.querySelector("div.codeAlertOffer") != null) {
                            document.querySelector("div.codeAlertOffer").remove()
                        }
                        codeValue.insertAdjacentHTML('beforebegin', codeAlertOffer)
                    } else {
                        if (document.querySelector("div.codeErrormsg") != null) {
                            document.querySelector("div.codeErrormsg").remove()
                        }
                        if (document.querySelector("div.codeAlertOffer") != null) {
                            document.querySelector("div.codeAlertOffer").remove()
                        }
                        title = data.offerTitle
                        offerInfo = `<h1 class="offerData"><div class="offer-title">${data.offerTitle}</div><div class="offer-price">${data.offerPrice}€</div></h1>`;
                        document.querySelector("#title-popup").insertAdjacentHTML('beforebegin', offerInfo)
                        document.querySelector("#title-popup").remove()
                        codeValue.insertAdjacentHTML('beforebegin', codeSuccessOffer)
                        buttonActivate.style.display = "none"
                        buttonActivate.insertAdjacentHTML('beforebegin', buttonCorrect)
                    }
                })
                .catch((error) => {
                    console.error('Error:', error)
                    alert('Algo ha salido mal')
                });


        })

        buttonCloseModal.addEventListener('click', function(e) {
            document.querySelector('#modal-one').classList.toggle('hidden')
            document.querySelector('#modal-one').classList.toggle('shown')
        })
    }

    document.querySelector('button#updateOffer').addEventListener('click', function() {
        let offer = {
            bar: {
                idbar: document.querySelector('#idbar').value
            },
            idOffer: document.querySelector('#idOffer').value,
            offerImage: document.querySelector('input[name="offerImage"').value,
            offerTitle: document.querySelector('input[name="offerTitle"').value,
            offerDescription: document.querySelector('input[name="offerDescription"').value,
            offerPrice: document.querySelector('input[name="offerPrice"').value,
            offerMinimunPoints: parseInt(document.querySelector('input[name="offerMinimunPoints"').value),
            offerRewardsPoints: parseInt(document.querySelector('input[name="offerRewardsPoints"').value),
            startDate: document.querySelector('input[name="startDate"').value,
            endDate: document.querySelector('input[name="endDate"').value,
        }

        fetch('/api/offers/' + idOffer.value, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(offer),
            })
            .then(response => response.text())
            .then(data => {
                console.log('Success:', data);
                location.href = '/admin/offers'
            })
            .catch((error) => {
                console.error('Error:', error);
            });

    })
}

function deleteOffer(id) {
    allowDelete = window.confirm('¿Estás seguro que deseas eliminar esta oferta?')
    if (allowDelete) {
        fetch('/api/offers/' + id, {
                method: 'DELETE',
            })
            .then(res => res.text())
            .then(data => {
                location.reload()
            })
    }
}

function editInNewPAge(id) {
    location.href = `offer/${id}/edit`
}

function editOffer(id) {
    let modal_two = document.querySelector("#modal-two")
    if (modal_two != null) { modal_two.remove() }
    fetch('/api/offers/' + id)
        .then(response => response.json())
        .then(data => {
            let modal_edit_offer =
                `<div class="modal shown" id="modal-two">
                <div class="modal-container">
                    <h1 id="title-popup">Editar oferta</h1>
                    <div class="modal-body">
                        <input type="hidden" value="${data.idOffer}" id="idOffer" />
                        <input type="hidden" value="${data.bar.idbar}" id="idbar" />
                        <h2>Imagen</h2>
                        <input  class="code-activation" value="${data.offerImage}" name="offerImage" />
                        <h2>Título</h2>
                        <input class="code-activation" value="${data.offerTitle}"name="offerTitle" />
                        <h2>Descripción</h2>
                        <input class="code-activation" value="${data.offerDescription}" name="offerDescription" />
                        <h2>Precio</h2>
                        <input class="code-activation" value="${data.offerPrice}" name="offerPrice" />
                        <h2>offerMinimunPoints</h2>
                        <input class="code-activation" value="${data.offerMinimunPoints}" name="offerMinimunPoints" />
                        <h2>offerRewardsPoints</h2>
                        <input class="code-activation" value="${data.offerRewardsPoints}" name="offerRewardsPoints" />
                        <h2>Fecha inicio</h2>
                        <input type="date" class="code-activation" value="${data.startDate}" name="startDate" />
                        <h2>Fecha fin</h2>
                        <input type="date" class="code-activation" value="${data.endDate}" name="endDate" />
                        <div class="edit-offer-actions">
                            <button id="button-modal-save" class="modal-submit">Guardar cambios</button>
                            <button id="modal-submit" class="modal-submit modal-exit">Cerrar</button>
                        </div>
                    </div>        
                <button id="close-modal" class="modal-close modal-exit">X</button>
                </div>
                </div>`;
            document.querySelector('body').insertAdjacentHTML('beforeend', modal_edit_offer)
            let xCloseModal = document.querySelectorAll(".modal-exit")
            xCloseModal.forEach(button => button.addEventListener('click', closeModal));
            let saveData = document.querySelector("#button-modal-save")
            saveData.addEventListener('click', saveChanges);
        });

    function closeModal(e) {
        document.querySelector('#modal-two').classList.toggle('hidden')
        document.querySelector('#modal-two').classList.toggle('shown')
    }

    function saveChanges() {
        let offer = {
            bar: {
                idbar: document.querySelector('#idbar').value
            },
            idOffer: document.querySelector('#idOffer').value,
            offerImage: document.querySelector('input[name="offerImage"').value,
            offerTitle: document.querySelector('input[name="offerTitle"').value,
            offerDescription: document.querySelector('input[name="offerDescription"').value,
            offerPrice: document.querySelector('input[name="offerPrice"').value,
            offerMinimunPoints: parseInt(document.querySelector('input[name="offerMinimunPoints"').value),
            offerRewardsPoints: parseInt(document.querySelector('input[name="offerRewardsPoints"').value),
            startDate: document.querySelector('input[name="startDate"').value,
            endDate: document.querySelector('input[name="endDate"').value,
        }

        fetch('/api/offers/' + idOffer.value, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(offer),
            })
            .then(response => response.text())
            .then(data => {
                console.log('Success:', data);
                location.reload()
            })
            .catch((error) => {
                console.error('Error:', error);
            });

    }

}




function closeSuccess() {
    location.reload()
}