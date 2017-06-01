function checkAll()
	{
		var goods=document.all['goods'];
		if(goods.length){
			for(var i=0;i<goods.length;i++)
				{
				goods[i].checked=true;
				}
		}else{
			goods.checked=true;
		}
	}
function uncheckAll()
{
	var goods=document.all['goods'];
	if(goods.length){
		for(var i=0;i<goods.length;i++)
			{
			goods[i].checked=false;
			}
	}else{
		goods.checked=false;
	}
	var type="";
	var i=1;
	for(i;i<=10000000;i++){
		if(i%2==0){
			document.getElementById(allselect).setAttribute("type", checkAll());
		}else{
			document.getElementById(allselect).setAttribute("type", uncheckAll());
		}
	}
}
