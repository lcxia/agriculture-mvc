// 手机二维码显示隐藏
window.onload=function () {
	var qrBtn=document.getElementById('qrBtn');
	var qrBox=document.getElementById('qrBox');
	var timer=null;

	qrBtn.onmouseover=qrBox.onmouseover=function(){
		qrBox.style.display="block";
		clearInterval(timer);
	};
	qrBtn.onmouseout=qrBox.onmouseout=function () {
		timer=setInterval(function () {
			qrBox.style.display="none";
		},300)
	}
}

// 图片轮换
$('.banner').unslider({
	speed: 500,               //  The speed to animate each slide (in milliseconds)
	delay: 3000,              //  The delay between slide animations (in milliseconds)
	complete: function() {},  //  A function that gets called after every slide animation
	keys: true,               //  Enable keyboard (left, right) arrow shortcuts
	dots: true,               //  Display dot navigation
	fluid: false              //  Support responsive design. May break non-responsive designs
});

// 图片轮换支持触屏滑动
var slides = jQuery('.banner'),
    i = 0;
slides
.on('swipeleft', function(e) {
  slides.eq(i + 1).addClass('active');
})
.on('swiperight', function(e) {
  slides.eq(i - 1).addClass('active');
});

jQuery('.banner')
.on('movestart', function(e) {
  // If the movestart is heading off in an upwards or downwards
  // direction, prevent it so that the browser scrolls normally.
  if ((e.distX > e.distY && e.distX < -e.distY) ||
      (e.distX < e.distY && e.distX > -e.distY)) {
    e.preventDefault();
  }
});

// 商品排序
$(function  () {
	$(".btn-sort a").click(function() {
		if($(this).hasClass('btn-danger')){
            $(this).find('i').toggleClass('up');

		}else{
		    $(this).addClass('btn-danger').siblings('a').removeClass('btn-danger')
		    $(this).siblings('a').find('i').removeClass('up')
		}
		
	});
})