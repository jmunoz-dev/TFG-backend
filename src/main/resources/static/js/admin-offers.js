window.onload = () => {
    let closeSession = document.querySelector('#cleanSession')
    if (closeSession != null) {
        closeSession.addEventListener('click', function() {
            var cookies = document.cookie.split(";");

            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i];
                var eqPos = cookie.indexOf("=");
                var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
            }
            location.reload()
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
                    .then(response => response.json())
                    .then(data => {
                        console.log('Success');
                        window.location = `/admin/offer/${data}/add/image`
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            })
        }

        if (buttonShowModal != null) {
            buttonShowModal.addEventListener('click', function(event) {
                window.scrollTo(0, 0);
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


        let deleteImgButton = document.querySelector('button[id^="delete-offer-img-"]')
        if (deleteImgButton != null) {
            deleteImgButton.addEventListener('click', function(event) {
                var idSplited = event.target.id.split("-")
                let idOffer = idSplited[3]
                if (!confirm("Estás seguro de que quieres eliminar esta imagen (Esta acción es irreversible)")) {
                    return
                }

                fetch('/admin/image/offer/' + idOffer + '/delete', {
                        method: 'DELETE'
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log('Success:', data);
                        window.location = '/admin/offer/' + idOffer + '/edit'

                    })
                    .catch((error) => {
                        alert("No se ha podido eliminar la imagen")
                        console.error('Error:', error);
                    });
            })
        }
    }
    if (document.querySelector('button#updateOffer') != null) {
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
}