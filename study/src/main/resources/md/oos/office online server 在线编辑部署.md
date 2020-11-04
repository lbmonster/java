## 	office online server 在线编辑部署

#### 一、简介

##### 此文档为office online server部署文档，实现word、excel在线编辑。

#### 二、需要什么？

##### 首先需要两台windows server 服务器，windows server 2012 r2和windows server 2016都可以。这里采用windows server 2012 r2。一台为域控服务器，一台为office online server 服务器。

#### 三、 搭建域控服务

1. 打开服务器管理器，点击添加角色和功能。

<img src="https://img-blog.csdn.net/20180602162407562?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" style="zoom: 67%;" />

2. 连续点三次下一步，选择添加AD域服务

   <img style="zoom: 170%;" src="https://img-blog.csdn.net/20180602162645523?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70"  />

   

![](https://img-blog.csdn.net/20180602162923855?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

3. 然后默认点击下一步到确认这一页，点击安装

![](https://img-blog.csdn.net/20180602163234450?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

4. 安装完成后，点击提升为域控制器

![](https://img-blog.csdn.net/20180602163506921?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

5. 会弹出新的窗口，选择[添加新林],并输入一个域名。

![](https://img-blog.csdn.net/20180602163724816?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

6. 点击下一步，输入密码，建议和用户密码保持一致。

![](https://img-blog.csdn.net/2018060216581931?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

7. 默认点击下一步。来到先决条件检查这里，如果出现这种情况，设置电脑密码后再点击检测

![](https://img-blog.csdn.net/20180604153208424?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

* 在cmd窗口设置一下administrator 的用户名和密码 并使用密码

  ```
  net user administrator "Abc123456"
  net user administrator /passwordreq:yes
  ```

8. 设置完成后点击安装

![](https://img-blog.csdn.net/20180604153947584?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

9. 安装成功后重启系统，域控制器的安装完成。

#### 四 、office online server 服务器加入到域

 1. 打开控制面板->网络->网络连接，修改转换服务器的NDS，指向域控服务器（刚才的服务器ip地址）

    ![](https://images0.cnblogs.com/blog/378600/201411/232049125315493.png)

	2. 在计算机属性中，修改计算机名称（随意），并添加到域控服务器（域为“添加新林”时设置的域名），确定后提示注销重启服务器。

    ![](https://images0.cnblogs.com/blog/378600/201411/232049263908124.png)

	3. 打开域控服务器，点击管理 > 添加服务器，输入转换服务器修改后的计算机名称，立即查找。将搜索到的服务器双击添加到右边，点击确定。 

    ![](https://images0.cnblogs.com/blog/378600/201411/232049412035300.png)

    ![](https://images0.cnblogs.com/blog/378600/201411/232049531095262.png)

	4. 在域控服务器中，所有服务器显示两台服务器，并都是联机状态则表示成功 。

    ![](https://images0.cnblogs.com/blog/378600/201411/232050079845951.png)

#### 五、安装office online server，首先下载office online server的安装包，和中文语言包。准备完成后，开始安装office online server必需的插件。

 1. 管理员打开powershell 输入以下命令

    ```
    Add-WindowsFeature Web-Server,Web-Mgmt-Tools,Web-Mgmt-Console,Web-WebServer,Web-Common-Http,Web-Default-Doc,Web-Static-Content,Web-Performance,Web-Stat-Compression,Web-Dyn-Compression,Web-Security,Web-Filtering,Web-Windows-Auth,Web-App-Dev,Web-Net-Ext45,Web-Asp-Net45,Web-ISAPI-Ext,Web-ISAPI-Filter,Web-Includes,InkandHandwritingServices,NET-Framework-Features,NET-Framework-Core,NET-HTTP-Activation,NET-Non-HTTP-Activ,NET-WCF-HTTP-Activation45,Windows-Identity-Foundation,Server-Media-Foundation
    ```

    

    ![](https://img-blog.csdn.net/20180604162955356?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

等待完成安装

![](https://img-blog.csdn.net/20180604163605134?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

* 安装以下软件

  <ul>
  <li><p><a href="https://go.microsoft.com/fwlink/p/?LinkId=510096" data-linktype="external"><span data-ttu-id="f032f-125">.NET Framework 4.5.2</span><span class="sxs-lookup"><span data-stu-id="f032f-125">.NET Framework 4.5.2</span></span></a></p>
  </li>
  <li><p><a href="https://www.microsoft.com/download/details.aspx?id=40784" data-linktype="external"><span data-ttu-id="f032f-126">Visual C++ Redistributable Packages for Visual Studio 2013</span><span class="sxs-lookup"><span data-stu-id="f032f-126">Visual C++ Redistributable Packages for Visual Studio 2013</span></span></a></p>
  </li>
  <li><p><a href="https://go.microsoft.com/fwlink/p/?LinkId=620071" data-linktype="external"><span data-ttu-id="f032f-127">Visual C++ Redistributable for Visual Studio 2015</span><span class="sxs-lookup"><span data-stu-id="f032f-127">Visual C++ Redistributable for Visual Studio 2015</span></span></a></p>
  </li>
  <li><p><a href="https://go.microsoft.com/fwlink/p/?LinkId=620072" data-linktype="external"><span data-ttu-id="f032f-128">Microsoft.IdentityModel.Extention.dll</span><span class="sxs-lookup"><span data-stu-id="f032f-128">Microsoft.IdentityModel.Extention.dll</span></span></a></p>
  </li>
  </ul>

  

可能会出现以下错误：

​	![](https://images2015.cnblogs.com/blog/391267/201612/391267-20161216103913870-1522104301.png)

* 解决方案

  <p><span style="font-size: 18px; font-family: 'Microsoft YaHei';">先安装补丁&nbsp;KB2919442&nbsp;<a href="https://www.microsoft.com/zh-cn/download/details.aspx?id=42153" target="_blank">立即下载基于 x64 的 Windows Server 2012 R2 的KB2919442补丁</a>。</span></p>
<p><span style="font-size: 18px; font-family: 'Microsoft YaHei';">下载地址&nbsp;<a href="https://www.microsoft.com/zh-cn/download/details.aspx?id=42153" target="_blank">https://www.microsoft.com/zh-cn/download/details.aspx?id=42153</a></span></p>
<p><span style="font-size: 18px; font-family: 'Microsoft YaHei';">KB2919442 &nbsp;安装完成后，继续安装&nbsp;Windows Server 2012 R2 Update (KB2919355) 所有补丁，如下：</span></p>
<p><span style="font-size: 18px; font-family: 'Microsoft YaHei';">下载地址 &nbsp;<a href="http://www.microsoft.com/downloads/details.aspx?FamilyId=373b1bb0-6d55-462e-98b7-6cb7d9ef1448" target="_blank">立即下载基于 x64 的 Windows Server 2012 R2 更新软件包。</a></span></p>
<p><span style="font-size: 18px; font-family: 'Microsoft YaHei';"><a href="http://www.microsoft.com/downloads/details.aspx?FamilyId=373b1bb0-6d55-462e-98b7-6cb7d9ef1448" target="_blank"><img src="https://images2015.cnblogs.com/blog/391267/201612/391267-20161216114039198-1297136464.png" alt=""></a></span></p>
<p><span style="font-size: 18px; font-family: 'Microsoft YaHei';"><a href="http://www.microsoft.com/downloads/details.aspx?FamilyId=373b1bb0-6d55-462e-98b7-6cb7d9ef1448" target="_blank"><img src="https://images2015.cnblogs.com/blog/391267/201612/391267-20161216114107089-657287846.png" alt=""></a></span></p>
下载所有插件，依次安装：

clearcompressionflag.exe　　　　　　　　　　　　38 KB　　　管理员身份运行，没有界面　　　

Windows8.1-KB2919355-x64.msu 　　　　　　　690.8 MB　　安装完成后，重启服务器，安装过程可能很慢

Windows8.1-KB2932046-x64.msu 　　　　　　　48.0 MB

Windows8.1-KB2934018-x64.msu 　　　　　　　126.4 MB

Windows8.1-KB2937592-x64.msu 　　　　　　　303 KB

Windows8.1-KB2938439-x64.msu 　　　　　　　19.6 MB

Windows8.1-KB2959977-x64.msu 　　　　　　  	2.8 MB

所有更新包安装完成后，接下来我们继续安装  Microsoft Visual C++ 2015 Redistributable (x64) - 14.0.23026	

![](https://images2015.cnblogs.com/blog/391267/201612/391267-20161216123255339-1848583132.png)



然后下载 Windows8.1-KB2999226-x64.msu并安装

<p><a href="https://www.microsoft.com/zh-cn/download/details.aspx?id=49071" data-linktype="external"><span data-stu-id="f032f-125">下载地址：Windows8.1-KB2999226-x64.msu</span></span></a></p>
##### 所有插件安装成功后，安装准备好的office server online

 1. 将下载好的office online server 2016的安装包解压好，并安装

    ![](https://img-blog.csdn.net/20180604165258990?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4OTcxODA2NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

一直点击下一步安装，安装完成后，安装中文语言包

​	![](https://img2018.cnblogs.com/blog/1248483/201906/1248483-20190606115331306-1510071514.png)

一直点击下一步安装，安装完成后设置，office online server 场

#### 创建Office Online Server服务器场

使用**New-OfficeWebAppsFarm**命令创建一个由单个服务器组成的新Office Online Server服务器场，如以下示例所示。powershell输入下面命令

```
New-OfficeWebAppsFarm -InternalURL "http://online.office.com"-ExternalUrl "http://192.168.8.132" -AllowHttp –EditingEnabled
```

**参数**

- **-InternalURL **内部访问地址，一般是http://机器名.AD域控地址
- **-AllowHttp** 是否允许http访问
- **-ExternalURL **外部访问地址，一般是服务器的ip地址
- **-EditingEnabled** 允许编辑office。

####  验证Office Online Server服务器场已成功创建

在服务器上访问 ：http://online.office.com/hosting/discovery 也就是你配置的域名

会发现通过域名访问不到。关闭域控防火墙。

然后管理员打开powershell输入下面命令：

```
Set-OfficeWebAppsFarm -AllowHttpSecureStoreConnections:$true
```

设置之后再次访问可以访问成功。

至此office online server 部署成功；

#### 六 、集成wopi实现在线编辑

​	首先获取java代码：git地址：https://github.com/ethendev/wopihost

​	修改以下配置

​	![](https://i.loli.net/2020/01/07/C3XWrfIPJ58ticD.png)

* file.path,代表你的文件路径。
* server.port当前服务端口。

配置好自己的文件路径和端口后启动项目。然后通过以下方式进行访问：

​	excel：

​	http://192.168.137.177/x/_layouts/xlviewerinternal.aspx?edit=1&WOPISrc=http://192.168.138.109:8080/wopi/files/yfsz.xlsx

​	word：

​	http://192.168.137.177/wv/wordviewerframe.aspx?edit=1&WOPISrc=http://192.168.138.109:8080/wopi/files/123.docx