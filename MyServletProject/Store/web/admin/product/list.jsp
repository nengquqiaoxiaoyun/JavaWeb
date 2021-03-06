<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<c:set value="${pageContext.request.contextPath}" var="root"></c:set>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${root}/css/Style1.css"
          rel="stylesheet" type="text/css"/>
    <script language="javascript"
            src="../../js/public.js"></script>
    <script type="text/javascript">
        function addProduct() {
            window.location.href = "${root}/category";
        }
    </script>
</HEAD>
<body>
<br>

<table cellSpacing="1" cellPadding="0" width="100%" align="center"
       bgColor="#f5fafe" border="0">
    <TBODY>
    <tr>
        <td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
        </TD>
    </tr>
    <tr>
        <td class="ta_01" align="right">
            <button type="button" id="add" name="add" value="添加"
                    class="button_add" onclick="addProduct()">
                &#28155;&#21152;
            </button>

        </td>
    </tr>
    <tr>
        <td class="ta_01" align="center" bgColor="#f5fafe">
            <table cellspacing="0" cellpadding="1" rules="all"
                   bordercolor="gray" border="1" id="DataGrid1"
                   style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                <tr
                        style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

                    <td align="center" width="18%">序号</td>
                    <td align="center" width="17%">商品图片</td>
                    <td align="center" width="17%">商品名称</td>
                    <td align="center" width="17%">商品价格</td>
                    <td align="center" width="17%">是否热门</td>
                    <td align="center" width="10%">所属分类</td>
                    <td width="7%" align="center">编辑</td>
                    <td width="7%" align="center">删除</td>
                </tr>


                <c:forEach items="${requestScope.product}" var="product" varStatus="status">
                    <tr onmouseover="this.style.backgroundColor = 'white'"
                        onmouseout="this.style.backgroundColor = '#F5FAFE';">
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                            width="18%">${status.index + 1}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                            width="17%"><img width="40" height="45" src="${root}/${product.pimage}"></td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                            width="17%">${product.pname}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                            width="17%">${product.shopPrice}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                            width="17%">${product.isHot == 0 ? "否" : "是"}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                            width="10%">${product.category.cname}
                        </td>
                        <td align="center" style="HEIGHT: 22px"><a
                                href="${root}/getProduct?pid=${product.pid}">
                            <img
                                    src="${root}/images/i_edit.gif"
                                    border="0" style="CURSOR: hand">
                        </a></td>

                        <td align="center" style="HEIGHT: 22px">
                                <a
                                        href="#" onclick="deleteItem('${product.pid}')">
                            <img
                                    src="${root}/images/i_del.gif"
                                    width="16" height="16" border="0" style="CURSOR: hand">
                        </a></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>

    </TBODY>
</table>
</body>

<script type="text/javascript">

    function deleteItem(pid) {
        let res = confirm("确定删除吗")
        if(res) {
            location.href = "${root}/deleteProduct?pid=" + pid
        }

    }

</script>
</HTML>

