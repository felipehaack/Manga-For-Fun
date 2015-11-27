
var app = {
    utils: {
        touchOrClick: {
            start: 'mousedown',
            move: 'mousemove',
            end: 'mouseup',
            init: function() {

                var t = "ontouchstart" in document.documentElement;

                if (t) {

                    app.utils.touchOrClick.start = 'touchstart';
                    app.utils.touchOrClick.move = 'touchmove';
                    app.utils.touchOrClick.end = 'touchend';
                }
            }
        },
        hasWebKit: function() {

            var t = 'WebkitAppearance' in document.documentElement.style;

            if (t)
                app.utils.hasWebKit = '-webkit-';
            else
                app.utils.hasWebKit = '';
        },
        init: function() {

            app.utils.hasWebKit();
            app.utils.touchOrClick.init();
        }
    },
    animation: {
    },
    bind: {
    },
    adjust: {
    },
    loaded: function() {
    },
    init: function() {

        window.onload = app.loaded;
    }
};

app.init();