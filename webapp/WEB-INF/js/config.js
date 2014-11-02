//var suffix = '${dui.devMode}' == 'false' ? '${dui.suffix}' : '';
//var suffix = '.min';
var suffix = '';
define('jquery', function(){
	return $;
});
define('config', {
    'baseUrl': '/static',
    'paths': {
        /* vendor */
    	'jquery': 'vendor/jquery/dist/jquery' + suffix,
    	'jqueryui': 'vendor/jquery-ui/jquery-ui' + suffix,
        'cookie': 'vendor/jquery.cookie' + suffix,
        'bootstrap': 'vendor/bootstrap/dist/js/bootstrap' + suffix,
        'galleria': 'vendor/galleria/src/galleria' + suffix,
        'lazyload': 'vendor/jquery.lazyload/jquery.lazyload' + suffix,
        'nouislider': 'vendor/nouislider/jquery.nouislider' + suffix,
        'tmpl': 'vendor/jquery.tmpl/jquery.tmpl' + suffix,
        'link': 'vendor/nouislider/Link' + suffix,
        'spin': 'vendor/spin' + suffix,
        'text': 'vendor/requirejs/text' + suffix,
        'loading': 'js/common' + suffix,
        'control': 'js/control' + suffix,
        'util': 'js/util' + suffix,
        'autocomplete': 'js/deyou-autocomplete' + suffix,
        'localization': 'js/localization' + suffix,
        'main': 'js/main' + suffix
    },
    'shim': {
    	'jqueryui': [ 'jquery' ],
        'tmpl': [ 'jquery' ],
        'bootstrap': [ 'jquery' ],
        'lazyload': [ 'jquery' ],
        'nouislider': {
            deps: [ 'link' ]
        },
        'link': [ 'jquery' ],
        'control': [ 'jquery' ],
        'autocomplete': [ 'jquery' ],
        'spin': {
            exports: 'Spinner'
        },
        'galleria': ['jquery']
    }
});
require(['config'], function(config){
    require.config(config);
});
