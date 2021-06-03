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

    if (/admin\/reservations/.test(window.location.href)) {

        let cancelReservationButtons = document.querySelectorAll('[id^="cancel-reservation-"]')
        cancelReservationButtons.forEach(button => {
            button.addEventListener('click', function(event) {

                var idSplited = event.target.id.split("-")
                let idReservation = idSplited[2]
                cancelReservation(idReservation)
            })
        })
    }
    if (/admin\/tables/.test(window.location.href)) {
        let deleteTableButtons = document.querySelectorAll('[id^="detele-table-"]')
        deleteTableButtons.forEach(button => {

            button.addEventListener('click', function(event) {
                var idSplited = event.target.id.split("-")
                let idTable = idSplited[2]
                deteleTable(idTable)
            })
        })
    }
    if (/admin\/tables\/edit/.test(window.location.href)) {
        let saveAttr = document.querySelector('#edit-table-save')
        saveAttr.addEventListener('click', function(event) {
            event.preventDefault()
            console.log("click")
            let num = document.forms['table-attr'].num.value
            let capacity = document.forms['table-attr'].capacity.value
            let idTable = document.querySelector("#tableId").value
            let table = {
                'num': num,
                'capacity': Number(capacity)
            }
            editTableAtributes(idTable, table)
        })

        let deleteSchButtons = document.querySelectorAll('[id^="delete-schedule-"]')
        deleteSchButtons.forEach(button => {
            button.addEventListener('click', function(event) {
                var idSplited = event.target.id.split("-")
                let idSchedule = idSplited[2]
                deleteSchedule(idSchedule)
            })
        })
        let newSchedule = document.querySelector('#new-schedule')
        newSchedule.addEventListener('click', function(event) {
            event.preventDefault();
            let idSchedule = document.forms['add-schedule'].schedule.value
            let idTable = document.forms['add-schedule'].tableId.value
            if (!idSchedule) {
                alert("completa todos los campos")
                return
            }
            let schedule = {
                'schedule': {
                    'idSchedule': Number(idSchedule)
                },
                'table': {
                    'idTable': Number(idTable)
                }
            }
            addSchedule(schedule)
        })

    }
    if (/admin\/tables\/new/.test(window.location.href)) {

        let newButton = document.querySelector('#new-table')
        let newSchedule = document.querySelector('#new-schedule')
        newButton.addEventListener('click', function(event) {
            event.preventDefault();
            let num = document.forms['add-table'].num.value
            let capacity = document.forms['add-table'].capacity.value
            let idBar = document.forms['add-table'].barId.value
            if (!num || !capacity || !idBar) {
                alert("completa todos los campos")
                return
            }
            let table = {
                'bar': {
                    'idbar': Number(idBar)
                },
                'capacity': Number(capacity),
                'num': num
            }
            createTable(table)
        })

        newSchedule.addEventListener('click', function(event) {
            event.preventDefault();
            let idSchedule = document.forms['add-schedule'].schedule.value
            let idTable = document.forms['add-schedule'].tableId.value
            if (!idSchedule) {
                alert("completa todos los campos")
                return
            }
            let schedule = {
                'schedule': {
                    'idSchedule': Number(idSchedule)
                },
                'table': {
                    'idTable': Number(idTable)
                }
            }
            addSchedule(schedule)
        })

    }
}



function deleteSchedule(idSchedule) {
    fetch('/api/tables/schedule/' + Number(idSchedule), {
            method: 'DELETE'
        })
        .then(res => res.json())
        .then(res => {
            console.log(res)

        }).catch(e => console.log(e))
}

function editTableAtributes(idTable, table) {

    fetch('/api/tables/' + Number(idTable), {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(table)
        })
        .then(res => res.json())
        .then(res => {
            location.reload()

        }).catch(e => console.log(e))

}

function cancelReservation(id) {
    console.log("id:", id)
    allowDelete = window.confirm('¿Estás seguro que deseas cancelar esta reserva?')
    if (allowDelete) {
        fetch('/api/reservations/cancel/' + Number(id), {
                method: 'PUT',
            })
            .then(res => res.json())
            .then(res => {
                if (res.status == 400) {
                    alert("oferta no encontrada")
                } else {
                    location.reload()
                }
            }).catch(e => console.log(e))
    }
}

function deteleTable(id) {
    allowDelete = window.confirm('Puede haber reservas futuras asociadas a esta mesa, si la eliminas estas reservas serán canceladas \n ¿Estás seguro que deseas eliminar esta mesa?')
    if (allowDelete) {
        fetch('/api/tables/' + Number(id), {
                method: 'DELETE',
            })
            .then(() => {
                location.reload()
            }).catch(e => {
                console.log(e)
                alert("se ha producido un error")
            })
    }
}

function createTable(table) {
    console.log("table:", table)

    fetch('/api/tables/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(table)
        })
        .then(res => res.json())
        .then(res => {
            if (res.status == 400) {
                alert("mesa no encontrada")
            }

            document.forms['add-table'].style.display = "none"
            document.forms['add-schedule'].tableId.value = res.idTable
            document.querySelector("#succesTable").style.display = "block"
            document.forms['add-schedule'].style.display = "block"
        }).catch(e => console.log(e))

}

function addSchedule(schedule) {
    console.log("table:", schedule)

    fetch('/api/tables/schedule', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(schedule)
        })
        .then(res => {
            location.reload()
        }).catch(e => console.log(e))

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

function editInNewPage(id) {
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

function editTable(id) {


}


function closeSuccess() {
    location.reload()
}