window.onload = () => {
    document.forms[0].onsubmit = (e) => {
        e.preventDefault();
        let user = {
            email: document.forms[0].email.value,
            password: document.forms[0].password.value
        }

        fetch('/api/users/sign-in-bar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user),
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data, user);
                if (data === true)
                    window.location = "/admin/home"
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
}