let staticCacheName = "pwa";

self.addEventListener("install", function (e) {
    e.waitUntil(
        caches.open(staticCacheName).then(function (cache) {
            return cache.addAll(["/"]);
        })
    );
});

self.addEventListener('activate', () => {
    self.clients.matchAll({
        type: 'window'
    }).then(windowClients => {
        windowClients.forEach((windowClient) => {
            windowClient.navigate(windowClient.url);
        });
    });
});

self.onfetch = function(event) {
    event.respondWith(
        caches.match(event.request)
            .then(function() {
                return fetch(event.request);
            })
    );
}