async function login(email, password) {
    $.ajax("http://localhost:8080/loginTokenGenerate", {
        method: 'POST',
        body: JSON.stringify({
            email,
            password
        }),
        success: function(token){
            localStorage.setItem('token', "Bearer " + token);
            return true;
        },
        error: function(err){
            console.log(err)
            return false;
        }
    });
}