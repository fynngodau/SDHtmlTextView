*The best HTML renderer I found.*

It is forked to be included as a submodule and does **not** include the `SDHtmlTextView` class that the upstream project provides. The reason is that you probably want to write your own `TextView` implementation that makes use of HTMLSpanner if you want to render images. Get inspired by [this one that I wrote for a project](https://codeberg.org/fynngodau/moodleDirect/src/branch/development/app/src/main/java/godau/fynn/moodledirect/view/ImageLoaderTextView.java).

There appears to be a family of deveratives of some HTML spanner library, and this is the one I chose, because – even though its exact history is hard to make out – it appears to be **based on [NightWhistler/HtmlSpanner](https://github.com/NightWhistler/HtmlSpanner) with some improvements**.

This library supports rendering tables.

## Usage

1. Add the library as a submodule  
   `git submodule add https://github.com/fynngodau/SDHtmlTextView.git`
2. Add the dependency to your app module's `build.gradle`:
   `implementation 'it.sysdata.mobile:htmlspanner:+'`
3. Substitute the dependency with your submodule in `settings.gradle`:
   ```
   includeBuild('SDHtmlTextView') {
       dependencySubstitution {
           substitute module('it.sysdata.mobile:htmlspanner') using project(':HtmlSpanner')
       }
   }
   ```

---

This section is copied from upstream's README.

4. Use HtmlSpanner in your TextView :

In the xml layout file define a simple TextView then in the Activity do

```java
        String html=loadStringFromAssetFile(this,"example.html");
        TextView tv = (TextView) findViewById(R.id.text);
        int col=tv.getSolidColor();
        HtmlSpanner htmlSpanner=new HtmlSpanner(tv.getCurrentTextColor(), tv.getTextSize());
        htmlSpanner.setBackgroundColor(col);
        tv.setText(htmlSpanner.fromHtml(html));
```

If you want to handle href you need to add
```java
        tv.setMovementMethod(LinkMovementMethod.getInstance());
```

You can alternatively use a builder to initialize the HtmlSpanner

```java
        HtmlSpanner htmlSpanner = new HtmlSpanner.Builder()
                                    .textColor(tv.getCurrentTextColor())
                                    .textSize(tv.getTextSize())
                                    .backgroundColor(tv.getSolidColor())
                                    .tableHeaderCenter(isTableHeaderCentered)
                                    .build();
```

the builder possible parameters are:
- `textColor`, this attribute is required since is used to initialize the htmlspanner with a custom text color;
- `textSize`, this attribute is required since is used to initialize the htmlspanner with a custom text size;
- `backgroundColor`, this attribute is required since is used to initialize the htmlspanner with a custom background color to handle html tags like "div" correctly;
- `tableHeaderCenter`, if this attribute is set to false or true it will define the centering of the table header fields in tables, by default table header is centered.

### HTML Tags supported 

* ``<i>``
* ``<em>``
* ``<cite>``
* ``<dfn>``
* ``<b>``
* ``<strong>``
* ``<blockquote>``
* ``<ul>``
* ``<ol>``
* ``<tt>``
* ``<code>``
* ``<style>``
* ``<br>``
* ``<p>``
* ``<div>``
* ``<span>``
* ``<big>``
* ``<small>``
* ``<pre>``
* ``<sub>``
* ``<sup>``
* ``<center>``
* ``<li>``
* ``<font size="..." color="..." face="...">``
* ``<h1>``, ``<h2>``, ``<h3>``, ``<h4>``, ``<h5>``, ``<h6>``
* ``<a href="...">``
* ``<img src="...">``
* ``<table>``
* ``<u>``
* ``<hr/>``


### CSS Tags supported 

* ``color``
* ``background-color``
* ``align``
* ``text-align``
* ``font-weight``
* ``font-style``
* ``font-family``
* ``font-size``
* ``line-height`` only with measures in px 
* ``margin-bottom``
* ``margin-top``
* ``margin-left``
* ``margin-right``
* ``margin``
* ``text-indent``
* ``display``
* ``border-style``
* ``border-color``
* ``border-width``
* ``border``
* ``text-decoration`` only underline and line-through 

## License
Copyright (C) 2017 Sysdata S.p.A.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


