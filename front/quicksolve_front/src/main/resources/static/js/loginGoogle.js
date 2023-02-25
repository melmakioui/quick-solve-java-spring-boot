window.onload = function () {
    const googleBtn = $('#google-btn');

    googleBtn.on('click', function (e) {
        e.preventDefault();
        google.accounts.id.initialize({
            client_id:
                "327064841381-lloi1on6ses7vmjr72h2lptajd3g14fn.apps.googleusercontent.com",
            callback: async (response) => {
                const credential = response.credential;
                const responseFetch = await fetch(
                    "/auth/google",
                    {
                        method: "POST",
                        body: credential,
                    }
                );
                if (responseFetch.ok) window.location.href = "/";
                else window.location.href = "/error";
            },
        });
        google.accounts.id.prompt();
    });
};