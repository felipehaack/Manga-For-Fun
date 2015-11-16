
var mainAPP = {
    utils: {
        touchOrClick: {
            start: 'mousedown',
            move: 'mousemove',
            end: 'mouseup',
            init: function() {

                var t = "ontouchstart" in document.documentElement;

                if (t) {

                    mainAPP.utils.touchOrClick.start = 'touchstart';
                    mainAPP.utils.touchOrClick.move = 'touchmove';
                    mainAPP.utils.touchOrClick.end = 'touchend';
                }
            }
        },
        hasWebKit: function() {

            var t = 'WebkitAppearance' in document.documentElement.style;

            if (t)
                mainAPP.utils.hasWebKit = '-webkit-';
            else
                mainAPP.utils.hasWebKit = '';
        },
        init: function() {

            mainAPP.utils.hasWebKit();
            mainAPP.utils.touchOrClick.init();
        }
    },
    animation: {
        arrow: function(child, up) {

            if (up) {

                $(child).css(mainAPP.utils.hasWebKit + 'transform', 'rotate(-90deg)');
            } else {

                $(child).css(mainAPP.utils.hasWebKit + 'transform', 'rotate(90deg)');
            }
        }
    },
    bind: {
        arrow: function() {

            $('#mangasCtn > ul li:nth-child(2)').bind(mainAPP.utils.touchOrClick.end, function() {

                var next = $(this).parent().next();

                if (next.css('display') === 'block') {

                    mainAPP.animation.arrow($(this).children().children(), true);

                    next.css('display', 'none');
                } else {

                    mainAPP.animation.arrow($(this).children().children(), false);

                    next.css('display', 'block');
                }
            });
        }
    },
    loaded: function() {

        mainAPP.utils.init();
        mainAPP.bind.arrow();
    },
    init: function() {

        window.onload = mainAPP.loaded;
    }
};

mainAPP.init();