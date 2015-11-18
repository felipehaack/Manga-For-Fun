
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
        arrow: function(child, up) {

            if (up) {

                $(child).css(app.utils.hasWebKit + 'transform', 'rotate(-90deg)');
            } else {

                $(child).css(app.utils.hasWebKit + 'transform', 'rotate(90deg)');
            }
        }
    },
    bind: {
        arrow: {
            lock: true,
            init: function() {

                $('.arrowCtn').mouseenter(function() {


                });

                $('.arrowCtn').mouseleave(function() {


                });

                $('.arrowCtn').bind(app.utils.touchOrClick.start, function() {

                    app.bind.arrow.lock = false;
                });

                $('.arrowCtn').bind(app.utils.touchOrClick.move, function() {

                    if (!app.bind.arrow.lock)
                        app.bind.arrow.lock = true;
                });

                $('.arrowCtn').bind(app.utils.touchOrClick.end, function() {

                    if (!app.bind.arrow.lock) {

                        var next = $(this).parent().parent().next();

                        if (next.height() > 0) {

                            app.animation.arrow($(this).children(), true);

                            next.height(0);
                        } else {

                            app.animation.arrow($(this).children(), false);

                            next.css('height', 'auto');
                        }
                    }
                });
            }
        },
        select: {
            lock: true,
            init: function() {

                $('.select').bind(app.utils.touchOrClick.start, function() {

                    app.bind.select.lock = false;
                });

                $('.select').bind(app.utils.touchOrClick.move, function() {

                    if (!app.bind.select.lock)
                        app.bind.select.lock = true;
                });

                $('.select').bind(app.utils.touchOrClick.end, function() {

                    if (!app.bind.select.lock) {
                        
                        var url = '';
                        var background = $(this).css('background-image');

                        if (background.indexOf('checkNormal') > -1)
                            url = 'checkHover';
                        else
                            url = 'checkNormal';
                        
                        $(this).css({
                            'background': 'url(imgs/main/' + url + '.png) transparent no-repeat',
                            'background-size': 'contain',
                            'background-position': '50% 50%'
                        });
                    }
                });
            }
        }
    },
    adjust: {
        arrow: function() {

            $('.manga:first').find('li:last').css('border-radius', '0 0 0 20px');
            $('.manga:last').find('li:last').css('border-radius', '20px 0 0 0');
        }
    },
    loaded: function() {

        app.adjust.arrow();
        app.utils.init();
        app.bind.arrow.init();
        app.bind.select.init();
    },
    init: function() {

        window.onload = app.loaded;
    }
};

app.init();