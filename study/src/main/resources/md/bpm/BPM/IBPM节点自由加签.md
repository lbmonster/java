# IBPM节点任务委派

##### 简介：如图1.0.1为例当任务在未结束前任意节点时，可将任务委派给其他人。假如当任务处在业务经理节点时，这时候业务经理A领取了任务，然后把任务委派给了业务经理B。这时候业务经理A无法完成该任务，只有业务经理B完成委派任务后，任务会自动回来业务经理A，由业务经理A继续推动流程。

![image-20201021135429909](C:\Users\intasect\AppData\Roaming\Typora\typora-user-images\image-20201021135429909.png)

##### 示例：当任务处在业务经理处，业务经理A领取后，任务转派给业务经理B或其他人时，需要调用如下接口

![image-20201021140136187](C:\Users\intasect\AppData\Roaming\Typora\typora-user-images\image-20201021140136187.png)

##### 接口定义如下：

<table style="text-align:center">
    <tr bgcolor="yellow">
        <td style="width:12%">接口名称</td>
        <td style="width:17%">接口调用方式</td>
        <td style="width:12%">参数</td>
        <td style="width:12%">能否为空</td>
        <td>参数含义</td>
    </tr>
    <tr>
        <td rowspan="7">回到原点</td>
        <td rowspan="7">POST</td>
    </tr>
    <tr>
        <td>ApiReq</td>
        <td>否</td>
        <td style="text-align:left">系统必传请求参数</td>
    </tr>
    <tr>
        <td>taskid</td>
        <td>否</td>
        <td style="text-align:left">当前流程所在节点的任务Id</td>
    </tr>
    <tr>
        <td>userName</td>
        <td>否</td>
        <td style="text-align:left">被委派人信息。</td>
    </tr>
</table>

##### 代码示例：

![image-20201021140815036](C:\Users\intasect\AppData\Roaming\Typora\typora-user-images\image-20201021140815036.png)

##### 调用完成后即任务被指派给指定用户。被指派人调用完成任务接口即可完成指派任务。

##### ![image-20201021140806263](C:\Users\intasect\AppData\Roaming\Typora\typora-user-images\image-20201021140806263.png)

##### 完成后由业务经理A即可继续推进流程。

### 流程示例：[流程文件](./file/lbtest4.bpmn20.xml)

