<h1 align='center'> Student's Chat - Web Project</h1>

<p> Developed by: </p>
<ul>
<li> Munarini Stefano </li>
<li> Bonadiman Gabriele </li>
</ul>

<p>University Project for "Intro to Web Programming" (University of Trento)</p>
<p>Teachers: Maurizio Marchese, Gino Perla</p>

<p> A forum for students where they can exchange ideas, infos, notes and solutions to exercises/exams. </p>


<h2 align='center'> Project Architecture </h2>

This project was developed in Java with Java Servlet & JSP (Java Servlet Page) technologies.

The design pattern used is MVC (Model-View-Controller).

The Server used is GlassFish 4.0.

<h4> Libraries used: </h4>
<ul>
<li> JSTL </li>
<li> Gson </li>
<li> JQuery </li>
</ul>

<h4> Database </h4>
The Database used is Derby. We designed it to have 3 tables and 2 relations, as follow:

	USER (id,username,password,email,avatar,user_type,date_time)
	POST (id, text, user_id*, group_id*,date_time,file)
	GROUP (id, name, creation_date, flag, owner*,state)
	USER_GROUP (id, user_id*, group_id*)
	INVITATION (id,user_id*,group_id*)

The 2 relations are represented by table USER_GROUP and INVITATION.

<h4> Packages: </h4>
<ul>
<li> Database </li>
<li> Controllers </li>
<li> Filters </li>
<li> Listeners </li>
<li> Services </li>
<li> Servlets </li>
</ul>
