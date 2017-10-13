/*!
 * jQuery blockUI plugin
 * Version 2.54 (17-DEC-2012)
 * @requires jQuery v1.3 or later
 *
 * Examples at: http://malsup.com/jquery/block/
 * Copyright (c) 2007-2012 M. Alsup
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 * Thanks to Amir-Hossein Sobhi for some excellent contributions!
 */

;(function() {
"use strict";

	function setup($) {
		if (/^1\.(0|1|2)/.test($.fn.jquery)) {
			/*global alert:true */
			alert('blockUI requires jQuery v1.3 or later!  You are using v' + $.fn.jquery);
			return;
		}

		$.fn._fadeIn = $.fn.fadeIn;

		var noOp = $.noop || function() {};

		// this bit is to ensure we don't call setExpression when we shouldn't (with extra muscle to handle
		// retarded userAgent strings on Vista)
		var msie = /MSIE/.test(navigator.userAgent);
		var ie6  = /MSIE 6.0/.test(navigator.userAgent);
		var mode = document.documentMode || 0;
		// var setExpr = msie && (($.browser.version < 8 && !mode) || mode < 8);
		var setExpr = $.isFunction( document.createElement('div').style.setExpression );

		// global $ methods for blocking/unblocking the entire page
		$.blockUI   = function(opts) {
			var curentZIndex=parseInt($(".blockMsg").last().css("z-index")||1000000)+1;
			var el= $("<div class='mycontainer' style='z-index: "+curentZIndex+"; border: none; margin: 0px; padding: 0px; width: 100%; height: 100%; top: 0px; left: 0px; cursor: default; position: fixed; background-color: rgba(0, 0, 0,0);'></div>").appendTo("body");
			install(el,opts);
			//install(window, opts); 
		};
		$.unblockUI = function(opts) { 
			if(jQuery(".mycontainer").last().length>0){
				remove(jQuery(".mycontainer").last(), opts);
				jQuery(".mycontainer").last().remove();
			}else{
				remove(window, opts);
			}
		};

		// convenience method for quick growl-like notifications  (http://www.google.com/search?q=growl)
		$.growlUI = function(title, message, timeout, onClose) {
			var $m = $('<div class="growlUI"></div>');
			if (title) $m.append('<h1>'+title+'</h1>');
			if (message) $m.append('<h2>'+message+'</h2>');
			if (timeout === undefined) timeout = 3000;
			$.blockUI({
				message: $m, fadeIn: 700, fadeOut: 1000, centerY: false,
				timeout: timeout, showOverlay: false,
				onUnblock: onClose,
				css: $.blockUI.defaults.growlCSS
			});
		};

		// plugin method for blocking element content
		$.fn.block = function(opts) {
			var fullOpts = $.extend({}, $.blockUI.defaults, opts || {});
			this.each(function() {
				var $el = $(this);
				if (fullOpts.ignoreIfBlocked && $el.data('blockUI.isBlocked'))
					return;
				$el.unblock({ fadeOut: 0 });
			});

			return this.each(function() {
				if ($.css(this,'position') == 'static')
					this.style.position = 'relative';
				this.style.zoom = 1; // force 'hasLayout' in ie
				install(this, opts);
			});
		};

		// plugin method for unblocking element content
		$.fn.unblock = function(opts) {
			return this.each(function() {
				remove(this, opts);
			});
		};

		$.blockUI.version = 2.54; // 2nd generation blocking at no extra cost!

		// override these in your code to change the default behavior and style
		$.blockUI.defaults = {
			// message displayed when blocking (use null for no message)
			message:  '<h1>Please wait...</h1>',

			title: null,		// title string; only used when theme == true
			draggable: true,	// only used when theme == true (requires jquery-ui.js to be loaded)

			theme: false, // set to true to use with jQuery UI themes

			// styles for the message when blocking; if you wish to disable
			// these and use an external stylesheet then do this in your code:
			// $.blockUI.defaults.css = {};
			css: {
				padding:	0,
				margin:		0,				
				top:		'40%',
				left:		'35%',				
				cursor:		'default'
			},

			// minimal style set used when themes are used
			themedCSS: {
				width:	'30%',
				top:	'40%',
				left:	'35%'
			},

			// styles for the overlay
			overlayCSS:  {
				backgroundColor:	'#000',
				opacity:			0.6,
				cursor:				'default'
			},

			// style to replace wait cursor before unblocking to correct issue
			// of lingering wait cursor
			cursorReset: 'default',

			// styles applied when using $.growlUI
			growlCSS: {
				width:		'350px',
				top:		'10px',
				left:		'',
				right:		'10px',
				border:		'none',
				padding:	'5px',
				opacity:	0.6,
				cursor:		'default',
				color:		'#fff',
				backgroundColor: '#000',
				'-webkit-border-radius':'10px',
				'-moz-border-radius':	'10px',
				'border-radius':		'10px'
			},

			// IE issues: 'about:blank' fails on HTTPS and javascript:false is s-l-o-w
			// (hat tip to Jorge H. N. de Vasconcelos)
			/*jshint scripturl:true */
			iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false' : 'about:blank',

			// force usage of iframe in non-IE browsers (handy for blocking applets)
			forceIframe: false,

			// z-index for the blocking overlay
			baseZ: 1000000,

			// set these to true to have the message automatically centered
			centerX: true, // <-- only effects element blocking (page block controlled via css above)
			centerY: true,

			// allow body element to be stetched in ie6; this makes blocking look better
			// on "short" pages.  disable if you wish to prevent changes to the body height
			allowBodyStretch: true,

			// enable if you want key and mouse events to be disabled for content that is blocked
			bindEvents: true,

			// be default blockUI will supress tab navigation from leaving blocking content
			// (if bindEvents is true)
			constrainTabKey: true,

			// fadeIn time in millis; set to 0 to disable fadeIn on block
			fadeIn:  200,

			// fadeOut time in millis; set to 0 to disable fadeOut on unblock
			fadeOut:  400,

			// time in millis to wait before auto-unblocking; set to 0 to disable auto-unblock
			timeout: 0,

			// disable if you don't want to show the overlay
			showOverlay: true,

			// if true, focus will be placed in the first available input field when
			// page blocking
			focusInput: true,

			// suppresses the use of overlay styles on FF/Linux (due to performance issues with opacity)
			// no longer needed in 2012
			// applyPlatformOpacityRules: true,

			// callback method invoked when fadeIn has completed and blocking message is visible
			onBlock: null,

			// callback method invoked when unblocking has completed; the callback is
			// passed the element that has been unblocked (which is the window object for page
			// blocks) and the options that were passed to the unblock call:
			//	onUnblock(element, options)
			onUnblock: null,

			// callback method invoked when the overlay area is clicked.
			// setting this will turn the cursor to a pointer, otherwise cursor defined in overlayCss will be used.
			onOverlayClick: null,

			// don't ask; if you really must know: http://groups.google.com/group/jquery-en/browse_thread/thread/36640a8730503595/2f6a79a77a78e493#2f6a79a77a78e493
			quirksmodeOffsetHack: 4,

			// class name of the message block
			blockMsgClass: 'blockMsg',

			// if it is already blocked, then ignore it (don't unblock and reblock)
			ignoreIfBlocked: false
		};

		// private data and functions follow...

		var pageBlock = null;
		var pageBlockEls = [];

		function install(el, opts) {
			var css, themedCSS;
			var full =(el == window);
			var msg = (opts && opts.message !== undefined ? opts.message : undefined);
			opts = $.extend({}, $.blockUI.defaults, opts || {});

			if (opts.ignoreIfBlocked && $(el).data('blockUI.isBlocked'))
				return;

			opts.overlayCSS = $.extend({}, $.blockUI.defaults.overlayCSS, opts.overlayCSS || {});
			css = $.extend({}, $.blockUI.defaults.css, opts.css || {});
			if (opts.onOverlayClick)
				opts.overlayCSS.cursor = 'pointer';

			themedCSS = $.extend({}, $.blockUI.defaults.themedCSS, opts.themedCSS || {});
			msg = msg === undefined ? opts.message : msg;

			// remove the current block (if there is one)
			if (full && pageBlock)
				remove(window, {fadeOut:0});

			// if an existing element is being used as the blocking content then we capture
			// its current place in the DOM (and current display style) so we can restore
			// it when we unblock
			if (msg && typeof msg != 'string' && (msg.parentNode || msg.jquery)) {
				var node = msg.jquery ? msg[0] : msg;
				var data = {};
				$(el).data('blockUI.history', data);
				data.el = node;
                if(node){
				data.parent = node.parentNode;
				data.display = node.style.display;
				data.position = node.style.position;
                }
				if (data.parent)
					data.parent.removeChild(node);
			}

			$(el).data('blockUI.onUnblock', opts.onUnblock);
			var z = opts.baseZ;

			// blockUI uses 3 layers for blocking, for simplicity they are all used on every platform;
			// layer1 is the iframe layer which is used to supress bleed through of underlying content
			// layer2 is the overlay layer which has opacity and a wait cursor (by default)
			// layer3 is the message content that is displayed while blocking
			var lyr1, lyr2, lyr3, s;
			if (msie || opts.forceIframe)
				lyr1 = $('<iframe class="blockUI" style="z-index:'+ (z++) +';display:none;border:none;margin:0;padding:0;position:absolute;width:100%;height:100%;top:0;left:0" src="'+opts.iframeSrc+'"></iframe>');
			else
				lyr1 = $('<div class="blockUI" style="display:none"></div>');

			if (opts.theme)
				lyr2 = $('<div class="blockUI blockOverlay ui-widget-overlay" style="z-index:'+ (z++) +';display:none"></div>');
			else
				lyr2 = $('<div class="blockUI blockOverlay" style="z-index:'+ (z++) +';display:none;border:none;margin:0;padding:0;width:100%;height:100%;top:0;left:0"></div>');

			if (opts.theme && full) {
				s = '<div class="blockUI ' + opts.blockMsgClass + ' blockPage ui-dialog ui-widget ui-corner-all" style="z-index:'+(z+10)+';display:none;position:fixed">';
				if ( opts.title ) {
					s += '<div class="ui-widget-header ui-dialog-titlebar ui-corner-all blockTitle">'+(opts.title || '&nbsp;')+'</div>';
				}
				s += '<div class="ui-widget-content ui-dialog-content"></div>';
				s += '</div>';
			}
			else if (opts.theme) {
				s = '<div class="blockUI ' + opts.blockMsgClass + ' blockElement ui-dialog ui-widget ui-corner-all" style="z-index:'+(z+10)+';display:none;position:absolute">';
				if ( opts.title ) {
					s += '<div class="ui-widget-header ui-dialog-titlebar ui-corner-all blockTitle">'+(opts.title || '&nbsp;')+'</div>';
				}  
				s += '<div class="ui-widget-content ui-dialog-content"></div>';
				s += '</div>';
			}
			else if (full) {
				s = '<div class="blockUI ' + opts.blockMsgClass + ' blockPage" style="z-index:'+(z+10)+';display:none;position:fixed"></div>';
			}
			else {
				s = '<div class="blockUI ' + opts.blockMsgClass + ' blockElement" style="z-index:'+(z+10)+';display:none;position:absolute"></div>';
			}
			lyr3 = $(s);

			// if we have a message, style it
			if (msg) {
				if (opts.theme) {
					lyr3.css(themedCSS);
					lyr3.addClass('ui-widget-content');
				}
				else
					lyr3.css(css);
			}

			// style the overlay
			if (!opts.theme /*&& (!opts.applyPlatformOpacityRules)*/)
				lyr2.css(opts.overlayCSS);
			lyr2.css('position', full ? 'fixed' : 'absolute');

			// make iframe layer transparent in IE
			if (msie || opts.forceIframe)
				lyr1.css('opacity',0.0);

			//$([lyr1[0],lyr2[0],lyr3[0]]).appendTo(full ? 'body' : el);
			var layers = [lyr1,lyr2,lyr3], $par = full ? $('body') : $(el);
			$.each(layers, function() {
				this.appendTo($par);
			});

			if (opts.theme && opts.draggable && $.fn.draggable) {
				lyr3.draggable({
					handle: '.ui-dialog-titlebar',
					cancel: 'li'
				});
			}

			// ie7 must use absolute positioning in quirks mode and to account for activex issues (when scrolling)
			var expr = setExpr && (!$.support.boxModel || $('object,embed', full ? null : el).length > 0);
			if (ie6 || expr) {
				// give body 100% height
				if (full && opts.allowBodyStretch && $.support.boxModel)
					$('html,body').css('height','100%');

				// fix ie6 issue when blocked element has a border width
				if ((ie6 || !$.support.boxModel) && !full) {
					var t = sz(el,'borderTopWidth'), l = sz(el,'borderLeftWidth');
					var fixT = t ? '(0 - '+t+')' : 0;
					var fixL = l ? '(0 - '+l+')' : 0;
				}

				// simulate fixed position
				$.each(layers, function(i,o) {
					var s = o[0].style;
					s.position = 'absolute';
					if (i < 2) {
						if (full)
							s.setExpression('height','Math.max(document.body.scrollHeight, document.body.offsetHeight) - (jQuery.support.boxModel?0:'+opts.quirksmodeOffsetHack+') + "px"');
						else
							s.setExpression('height','this.parentNode.offsetHeight + "px"');
						if (full)
							s.setExpression('width','jQuery.support.boxModel && document.documentElement.clientWidth || document.body.clientWidth + "px"');
						else
							s.setExpression('width','this.parentNode.offsetWidth + "px"');
						if (fixL) s.setExpression('left', fixL);
						if (fixT) s.setExpression('top', fixT);
					}
					else if (opts.centerY) {
						if (full) s.setExpression('top','(document.documentElement.clientHeight || document.body.clientHeight) / 2 - (this.offsetHeight / 2) + (blah = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + "px"');
						s.marginTop = 0;
					}
					else if (!opts.centerY && full) {
						var top = (opts.css && opts.css.top) ? parseInt(opts.css.top, 10) : 0;
						var expression = '((document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + '+top+') + "px"';
						s.setExpression('top',expression);
					}
				});
			}

			// show the message
			if (msg) {
				if (opts.theme)
					lyr3.find('.ui-widget-content').append(msg);
				else
					lyr3.append(msg);
				if (msg.jquery || msg.nodeType)
					$(msg).show();
			}

			if ((msie || opts.forceIframe) && opts.showOverlay)
				lyr1.show(); // opacity is zero
			if (opts.fadeIn) {
				var cb = opts.onBlock ? opts.onBlock : noOp;
				var cb1 = (opts.showOverlay && !msg) ? cb : noOp;
				var cb2 = msg ? cb : noOp;
				if (opts.showOverlay)
					lyr2._fadeIn(opts.fadeIn, cb1);
				if (msg)
					lyr3._fadeIn(opts.fadeIn, cb2);
			}
			else {
				if (opts.showOverlay)
					lyr2.show();
				if (msg)
					lyr3.show();
				if (opts.onBlock)
					opts.onBlock();
			}

			// bind key and mouse events
			bind(1, el, opts);

			if (full||el.hasClass("mycontainer")) {
				pageBlock = lyr3[0];
				pageBlockEls = $(':input:enabled:visible',pageBlock);
				if (opts.focusInput)
					setTimeout(focus, 20);
			}
			else
				center(lyr3[0], opts.centerX, opts.centerY);

			if (opts.timeout) {
				// auto-unblock
				var to = setTimeout(function() {
					if (full)
						$.unblockUI(opts);
					else
						$(el).unblock(opts);
				}, opts.timeout);
				$(el).data('blockUI.timeout', to);
			}
		}

		// remove the block
		function remove(el, opts) {
			var full = (el == window);
			var $el = $(el);
			var data = $el.data('blockUI.history');
			var to = $el.data('blockUI.timeout');
			if (to) {
				clearTimeout(to);
				$el.removeData('blockUI.timeout');
			}
			opts = $.extend({}, $.blockUI.defaults, opts || {});
			bind(0, el, opts); // unbind events

			if (opts.onUnblock === null) {
				opts.onUnblock = $el.data('blockUI.onUnblock');
				$el.removeData('blockUI.onUnblock');
			}

			var els;
			if (full) // crazy selector to handle odd field errors in ie6/7
				els = $('body').children().filter('.blockUI').add('body > .blockUI');
			else
				els = $el.find('>.blockUI');

			// fix cursor issue
			if ( opts.cursorReset ) {
				if ( els.length > 1 )
					els[1].style.cursor = opts.cursorReset;
				if ( els.length > 2 )
					els[2].style.cursor = opts.cursorReset;
			}

			if (full)
				pageBlock = pageBlockEls = null;

			if (opts.fadeOut) {
				els.fadeOut(opts.fadeOut);
				setTimeout(function() { reset(els,data,opts,el); }, opts.fadeOut);
			}
			else
				reset(els, data, opts, el);
		}

		// move blocking element back into the DOM where it started
		function reset(els,data,opts,el) {
			els.each(function(i,o) {
				// remove via DOM calls so we don't lose event handlers
				if (this.parentNode)
					this.parentNode.removeChild(this);
			});

			if (data && data.el) {
				data.el.style.display = data.display;
				data.el.style.position = data.position;
				if (data.parent)
					data.parent.appendChild(data.el);
				$(el).removeData('blockUI.history');
			}

			if (typeof opts.onUnblock == 'function')
				opts.onUnblock(el,opts);

			// fix issue in Safari 6 where block artifacts remain until reflow
			var body = $(document.body), w = body.width(), cssW = body[0].style.width;
			body.width(w-1).width(w);
			body[0].style.width = cssW;
		}

		// bind/unbind the handler
		function bind(b, el, opts) {
			var full = el == window, $el = $(el);

			// don't bother unbinding if there is nothing to unbind
			if (!b && (full && !pageBlock || !full && !$el.data('blockUI.isBlocked')))
				return;

			$el.data('blockUI.isBlocked', b);

			// don't bind events when overlay is not in use or if bindEvents is false
			if (!opts.bindEvents || (b && !opts.showOverlay))
				return;

			// bind anchors and inputs for mouse and key events
			var events = 'mousedown mouseup keydown keypress keyup touchstart touchend touchmove';
			if (b)
				$(document).bind(events, opts, handler);
			else
				$(document).unbind(events, handler);

		// former impl...
		//		var $e = $('a,:input');
		//		b ? $e.bind(events, opts, handler) : $e.unbind(events, handler);
		}

		// event handler to suppress keyboard/mouse events when blocking
		function handler(e) {
			// allow tab navigation (conditionally)
			if (e.keyCode && e.keyCode == 9) {
				if (pageBlock && e.data.constrainTabKey) {
					var els = pageBlockEls;
					var fwd = !e.shiftKey && e.target === els[els.length-1];
					var back = e.shiftKey && e.target === els[0];
					if (fwd || back) {
						setTimeout(function(){focus(back);},10);
						return false;
					}
				}
			}
			var opts = e.data;
			var target = $(e.target);
			if (target.hasClass('blockOverlay') && opts.onOverlayClick)
				opts.onOverlayClick();

			// allow events within the message content
			if (target.parents('div.' + opts.blockMsgClass).length > 0)
				return true;

			// allow events for content that is not being blocked
			return target.parents().children().filter('div.blockUI').length === 0;
		}

		function focus(back) {
			if (!pageBlockEls)
				return;
			var e = pageBlockEls[back===true ? pageBlockEls.length-1 : 0];
			if (e)
				e.focus();
		}

		function center(el, x, y) {
			var p = el.parentNode, s = el.style;
			var l = ((p.offsetWidth - el.offsetWidth)/2) - sz(p,'borderLeftWidth');
			var t = ((p.offsetHeight - el.offsetHeight)/2) - sz(p,'borderTopWidth');
			if (x) s.left = l > 0 ? (l+'px') : '0';
			if (y) s.top  = t > 0 ? (t+'px') : '0';
		}

		function sz(el, p) {
			return parseInt($.css(el,p),10)||0;
		}

	}


	/*global define:true */
	if (typeof define === 'function' && define.amd && define.amd.jQuery) {
		define(['jquery'], setup);
	} else {
		setup(jQuery);
	}

})();
(function(){
	function myDrag(a,o){ 
		function mouseCoords(ev) 
		{ 
//			if(ev.pageX || ev.pageY){ 
//				return {x:ev.pageX, y:ev.pageY}; 
//			} 
			return{ 
				x:ev.clientX + document.body.scrollLeft - document.body.clientLeft, 
				y:ev.clientY + document.body.scrollTop - document.body.clientTop 
			};
		}
		//设置位置
		var left=jQuery(o).css("left");
		var top=jQuery(o).css("top");
		jQuery(o).css({left: left=="auto"?0:left,top:top=="auto"?0:top});
	     var d=document;if(!a)a=window.event; 
	     var mousePos = mouseCoords(a); 
	     var x=mousePos.x,y=mousePos.y;
	     if(o.setCapture) 
	         o.setCapture(); 
	     else if(window.captureEvents) 
	         window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP); 
	     var backData = {x : o.style.top, y : o.style.left}; 
	     var b=true;
	     d.onmousemove=function(a){ 
	         if(!a)a=window.event; 
	         var mousePos = mouseCoords(a); 
	         var tx=mousePos.x-x+parseInt(o.style.left),ty=mousePos.y-y+parseInt(o.style.top);
	         o.style.left=tx+"px"; 
	         o.style.top=ty+"px"; 
	         x=mousePos.x;
	         y=mousePos.y;
	     }; 
	     d.onmouseup=function(a){ 
	         if(!a)a=window.event; 
	         if(o.releaseCapture) 
	             o.releaseCapture(); 
	         else if(window.captureEvents) 
	             window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP); 
	         d.onmousemove=null; 
	         d.onmouseup=null; 
	         if(!a.pageX)a.pageX=a.clientX; 
	         if(!a.pageY)a.pageY=a.clientY; 
	         if(!document.body.pageWidth)document.body.pageWidth=document.body.clientWidth; 
	         if(!document.body.pageHeight)document.body.pageHeight=document.body.clientHeight; 
	         if(a.pageX < 1 || a.pageY < 1 || a.pageX > document.body.pageWidth || a.pageY > document.body.pageHeight){ 
	             o.style.left = backData.y; 
	             o.style.top = backData.x; 
	         } 
	     }; 
	}
	//拖动
	jQuery(document).on("mousedown", ".win .drag",function(event) {
		var win=jQuery(this).parents('.win-warp').first();
		if(win.length>0){
			myDrag(event,win.get(0));
		}
	})
}());

(function(){
	//改变大小
	resizeWin();
	jQuery(window).resize(function(){setTimeout(resizeWin,100)});
	var lastTime=(new Date()).getTime();//上次触发事件的时间；
	function resizeWin(){
		var curent=(new Date()).getTime();
		if(curent-lastTime<100){//太频繁IE8会死
			return;
		}
		lastTime=curent;
		//var win=jQuery(".win:visible");
		//if(win.length>0)return;//parent page
		jQuery(".data-list").css("height", "");
		jQuery(".data-content").css("height","");
		var bodyH= jQuery(window).height();
		var serchH=jQuery(".search-box").height();
		var listH=Math.max((bodyH-serchH-12)||0,355);
		var list=jQuery(".data-list");
		list.css("height", listH+"px");
		var dataContentH=listH-(list.find(".pagelink").length>0?(list.find(".pagelink").height()+10):0);
		jQuery(".data-content").css("height", dataContentH+"px");
	}
	//最大化
	jQuery(document).on("click",".maxBtn",function(){
		var win=jQuery(this).parents(".win").first();
		var winWarp=win.find(".win-warp");
		var frm=win.find("iframe");
		win.toggleClass("win-max");
		if(win.hasClass("win-max")){
			var width=jQuery(window).width()||jQuery(".win").width();
			var height=jQuery(window).height()||jQuery(".win").height();
			win.css({
				"width":width,
				"height":height
			});
			winWarp.css({"top":-((winWarp.offset().top-jQuery(document).scrollTop())||0)+"px","left":0})
			var topH=win.find(".top").length>0?win.find(".top").height()+20:0;
			frm.css({
				"height":(height-win.find(".win-title").height()-win.find(".buttons").height()-topH-20)+"px",
				"width":(width-20)+"px"
				});
		}else{
			win.css({"width":"","height":""});
			winWarp.css({"top":"","left":""});
			frm.css({"height":"","width":""});
		}
	});
	
}())

jQuery(function(){
	jQuery(document).on("click",".closeBtn,.closePop", function() {
		jQuery(this).parents(".win").find('iframe').attr('src', '');
		jQuery.unblockUI();
	});
	jQuery(window).resize();
});
function ShowPoplayer(divId){
	jQuery.blockUI({
		message : divId,
		css : {	top : "10%",left:"0",	right : "0"},
		overlayCSS : {opacity : 0.1}
	});
	//重置win
	var win=jQuery(divId);
	var winWarp=win.find(".win-warp");
	var frm=win.find("iframe");
	win.removeClass("win-max");
	win.css({"width":"","height":""});
	winWarp.css({"top":"","left":""});
	frm.css({"height":frm.attr("data-height")||"","width":""});
	jQuery(window).resize();
}
//处理提示
(function() {
	window._alert=window.alert;
	window.alert = function(msg,title,callFunc) {
		var title=title||"提示";
		var ok_id="btn_ok"+(new Date()).getTime();
		var div='<div class="win win-warp2" style="display:block;">\
					<div class="win-title">\
						<span class="title-text">'+title+'</span><a title="关闭" class="closeBtn"></a>\
					</div>\
					<div class="content2">\
						<div class="msg">\
							<p class="">'+msg+'</p>\
						</div>\
						<div class="buttons">\
							<input type="button" value="确定" class="btn-ok closePop" id="'+ok_id+'"/>\
						</div>\
					</div>\
				</div>'	;
		jQuery.blockUI({message:div,css:{top:"20%",left:"0",right:"0"},overlayCSS :{opacity : 0}});
		if(typeof callFunc=='function'){
			jQuery(document).on("click","#"+ok_id,callFunc);
		}
	}
	//删除确认
	window.DelConfirm = function(msg, callFunc) {
		var ok_id="btn_ok"+(new Date()).getTime();
		var div='<div class="win win-warp2" style="display:block;">\
					<div class="win-title">\
						<span class="title-text">提示</span><a title="关闭" class="closeBtn"></a>\
					</div>\
					<div class="content2">\
						<div class="msg">\
							<p >'+msg+'</p>\
						</div>\
						<div class="buttons">\
							<input type="button" value="是(Y)" class="btn-ok closePop" id="'+ok_id+'"/>\
							<input type="button" value="否(N)" class="btn-cancel closePop"/>\
						</div>\
					</div>\
				</div>'	;
		jQuery.blockUI({message:div,css:{top:"20%",left:"0",right:"0"},overlayCSS :{opacity : 0}});
		if(typeof callFunc=='function'){
			jQuery(document).on("click","#"+ok_id,callFunc);
		}
	};
	window.DelSuccess = function(msg, beforeFunc,afterFunc) {
		var ok_id="_btn_ok"+(new Date()).getTime();
		var cancel_id="_btn_cancel"+(new Date()).getTime();
		var div='<div class="win win-warp2" style="display:block;">\
					<div class="win-title">\
						<span class="title-text">提示</span><a title="关闭" class="closeBtn" id="'+cancel_id+'"></a>\
					</div>\
					<div class="content2">\
						<div class="msg">\
							<p class="">'+msg+'</p>\
							<p style="margin-top:10px;color:#A3A3A3;"><span class="sp_second">5</span> 秒后将自动关闭</p>\
						</div>\
						<div class="buttons" style="display:none;">\
							<input type="button" value="确定" class="btn-cancel closePop" id="'+ok_id+'"/>\
						</div>\
					</div>\
				</div>'	;
		jQuery.blockUI({message:div,css:{top:"20%",left:"0",right:"0"},overlayCSS :{opacity : 0}});
		if(typeof beforeFunc=='function'){
			beforeFunc();
		}
		jQuery("#"+ok_id).click(function(){
			if(typeof afterFunc=='function'){
				afterFunc();
			}
		})
		jQuery("#"+cancel_id).click(closeWin);
		function closeWin(){jQuery("#"+ok_id).click();}
		function autoClose(){
			setTimeout(function(){
				var sec=parseInt(jQuery(".sp_second").text());
				if(sec){
					if(sec==1){	
						closeWin();
					}else{
						jQuery(".sp_second").text(sec-1);
						autoClose();
					}
				}
			},1000);
		}
		autoClose();
	};
	window.DelSuccessForAllFeresh = function(msg, callFunc) {
		DelSuccess(msg,null,callFunc);
	};
	window.ShowMsg=function(html,seconds,title,callFunc){
		ShowMsg2(html,false,seconds,title,callFunc);
	}
	window.ShowMsgAutoClose=function(html,seconds,title,callFunc){
		ShowMsg2(html,true,seconds,title,callFunc);
	}
	window.ShowMsg2 = function(html,isAutoClose,seconds,title,callFunc) {
		var cancel_id="_btn_cancel"+(new Date()).getTime();
		var title=title||"提示";
		var div='<div class="win win-warp2" style="display:block;">\
					<div class="win-title">\
						<span class="title-text">'+title+'</span><a title="关闭" class="closeBtn" id="'+cancel_id+'"></a>\
					</div>\
					<div class="content2">\
						<div class="msg">\
							<p class="">'+html+'</p>';
							if(isAutoClose)
								div+='<p style="margin-top:10px;color:#A3A3A3;"><span class="sp_second">'+(seconds||5)+'</span> 秒后将自动关闭</p>';
						div+='\
						</div>\
					</div>\
				</div>'	;
		jQuery.blockUI({message:div,css:{top:"20%",left:"0",right:"0"},overlayCSS :{opacity : 0}});
		jQuery("#"+cancel_id).click(function(){
			if(typeof callFunc=='function'){
				callFunc();
			}
		});
		function autoClose(){
			setTimeout(function(){
				var sec=parseInt(jQuery(".sp_second").text());
				if(sec){
					if(sec==1){	jQuery("#"+cancel_id).click();}
					else{
						jQuery(".sp_second").text(sec-1);
						autoClose();
					}
				}
			},1000);
		}
		if(isAutoClose){
			autoClose();
		}
	};
}());
//自定义面板
function MyPanel(options){
	var setting={
		panelName:"myPanel"+((new Date()).getTime()),
		title:"请选择...",
		url:"",
		btnMax:true,
		btnClose:true,
		btnOk:false,
		btnCanel:true,
		move:true,
		css:{
			iframeHeight:"420px"//iframe的高度
		},
		okFunc:function(){
			alert("没有指定函数okFunc...");
		}
	}
	jQuery.extend(setting, options);
	var obj=this;
	function initial(){
		var winId="pop_"+setting.panelName;
		var framName=setting.panelName;
		var okId="btn_"+winId+(new Date()).getTime();
		var okFunc=(typeof setting.okFunc=="function")?setting.okFunc:function(){alert("没有指定函数okFunc...");};
		var winHtml='\
	<div class="win"  id="'+winId+'" >\
		<div class="win-warp">\
			<div class="win-container">\
				<div class="win-title '+(setting.move?"drag":"")+'">\
					<span>'+setting.title+'</span>\
					'+(setting.btnClose?'<a title="关闭" class="closeBtn"></a>':'')+'\
					'+(setting.btnMax?'<a title="最大化" class="maxBtn"></a>':'')+'\
				</div>\
				<iframe name="'+framName+'" frameborder="0" url="'+setting.url+'" \
					'+(setting.css.iframeHeight?'data-height="'+setting.css.iframeHeight+'" style="height:'+setting.css.iframeHeight+';"':'')+'></iframe>\
				<div class="buttons">\
					<div align="center">\
						'+(setting.btnOk?'<input type="button" value="确定" class="btn-ok" id="'+okId+'"/>':'')+'\
						'+(setting.btnCanel?'<input type="button" value="关闭" class="btn-cancel closePop"/>':'')+'\
					</div>\
				</div>\
			</div>\
		</div>\
	</div>\
			';
		if(jQuery("#"+winId).length>0){
			jQuery("#"+winId).remove();
		}
		jQuery("body").append(winHtml);
		jQuery(document).off("click","#"+okId);
		jQuery(document).on("click","#"+okId,okFunc);
		obj.panelId=winId;
		obj.panel=window.frames[framName];
	}
	obj.show=function(param){
		var frame=jQuery("#"+obj.panelId).find("iframe");
		jQuery.extend(setting, param);
		var src=setting.url || frame.attr("url");
		if(src)frame.attr("src",src);
		ShowPoplayer(jQuery("#"+obj.panelId));
	}
	initial();
	return obj;
}
