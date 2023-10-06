# Verbesserung Aufgabe 2

# Feedback

- Konvertierung klappt nicht für BinTxt nach CT:

```
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readCtFile: trying to read for object:aufgabe2.Person@548c4f57
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Name was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Birthdate was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Weight was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Height was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Identification Number was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Image path was read and saved.

Dimensions of the CT-Data [X,Y,Z]:[256, 256, 175] 
java.lang.NumberFormatException: For input string: "name"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
	at java.base/java.lang.Integer.parseInt(Integer.java:668)
	at java.base/java.lang.Short.parseShort(Short.java:137)
	at java.base/java.lang.Short.parseShort(Short.java:163)
	at aufgabe2.CtFileReader.readCtData(CtFileReader.java:190)
	at aufgabe2.CtFileReader.readFileSwitch(CtFileReader.java:155)
	at aufgabe2.CtFileReader.readFile(CtFileReader.java:96)
	at aufgabe2.CtFileReader.readCtFile(CtFileReader.java:36)
	at aufgabe2.CtConverter.txtAndBinToCt(CtConverter.java:50)
	at aufgabe2.CtConverterDemoMain.main(CtConverterDemoMain.java:19)
Exception in thread "main" java.lang.NumberFormatException: the file have been corrupt some data in the file wasn´t properly formatted.
	at aufgabe2.CtConverter.txtAndBinToCt(CtConverter.java:55)
	at aufgabe2.CtConverterDemoMain.main(CtConverterDemoMain.java:19)

Process finished with exit code 1

```

bei folgendem Aufruf

```
        //cv.txtAndBinToCt("/home/zowallar/Schreibtisch/demo/sample-data/dataViewer2.txt","/home/zowallar/Schreibtisch/demo/sample-data/dataViewer2.bin");
```

mit den Daten aus dem ILIAS.

- Maven Build schlägt fehl in der Test-Phase


```
[INFO] Running aufgabe2.CtConverterTest
Test Folder is at : /home/zowallar/IdeaProjects/gruppe09/CtConverter/ConvertedFiles
22.11.2022, 16:02:22 INFO: aufgabe2.CtFileReader.readCtFile: trying to read for object:aufgabe2.Person@2fd6b6c7
22.11.2022, 16:02:22 ERROR: aufgabe2.CtFileReader.readFile: Failure at Reading Txt- or CT-File
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.045 s <<< FAILURE! - in aufgabe2.CtConverterTest
[ERROR] aufgabe2.CtConverterTest  Time elapsed: 0.045 s  <<< ERROR!
java.lang.RuntimeException
	at aufgabe2.CtFileReader.readFile(CtFileReader.java:101)
	at aufgabe2.CtFileReader.readCtFile(CtFileReader.java:59)
	at aufgabe2.CtConverterTest.setupPerson(CtConverterTest.java:37)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
	at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
	at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
	at org.junit.jupiter.engine.extension.TimeoutExtension.interceptLifecycleMethod(TimeoutExtension.java:128)
	at org.junit.jupiter.engine.extension.TimeoutExtension.interceptBeforeAllMethod(TimeoutExtension.java:70)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeBeforeAllMethods$13(ClassBasedTestDescriptor.java:411)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeBeforeAllMethods(ClassBasedTestDescriptor.java:409)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:215)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:84)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:148)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:147)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:127)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:90)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:55)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:102)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:54)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:114)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:86)
	at org.junit.platform.launcher.core.DefaultLauncherSession$DelegatingLauncher.execute(DefaultLauncherSession.java:86)
	at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:55)
	at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:223)
	at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:175)
	at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:139)
	at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:456)
	at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:169)
	at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:595)
	at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:581
```

Zum Laden der Dateien besser den ContextClassLoader des aktuellen Threads verwenden. Dann die Testdaten auch in `src/test/resources` ablegen. Auf diese Weise bekommt man dann einen absoluten Pfad auf dem man arbeiten kann.

- Module (artifactId) sollten konsistent zum Ordnernamen auf Dateisysteme benannt werden.
- Capslock in GroupID besser vermeiden und "klein" schreiben.
- Ihr "verschluckt" sehr oft den Grund einer Exception. Besser wäre es die gefangene Exception der neu geworfenen Exception im Konstruktor zu übergeben. Auf diese Weise kann der Aufrufer dann auch den Grund für einen Fehler feststellen und verliert nicht die Stacktrace informationen der zugrunde liegenden Exception, weil nur eine RuntimeException ohne Angabe einer Message oder Exception geworfen wird.
- System.out.println in der Converter Implementierung durch Logger ersetzen!
- Das `close()` auf einem Reader/Writer gehört entweder in einen `finally`-Block **oder** in einen `try-with-resources`-Block. Wird es einfach im `try { .. }` aufgerufen, dann entsteht u.U. ein "MemoryLeak", da der Reader/Writer auf Grund einer vorangegangenen Exception nicht geschlossen werden konnte.


# Verbesserung

# Feedback

- Konvertierung klappt nicht für BinTxt nach CT:

```
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readCtFile: trying to read for object:aufgabe2.Person@548c4f57
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Name was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Birthdate was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Weight was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Height was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Identification Number was read and saved.
22.11.2022, 15:58:21 INFO: aufgabe2.CtFileReader.readFileSwitch: Image path was read and saved.

Dimensions of the CT-Data [X,Y,Z]:[256, 256, 175] 
java.lang.NumberFormatException: For input string: "name"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
	at java.base/java.lang.Integer.parseInt(Integer.java:668)
	at java.base/java.lang.Short.parseShort(Short.java:137)
	at java.base/java.lang.Short.parseShort(Short.java:163)
	at aufgabe2.CtFileReader.readCtData(CtFileReader.java:190)
	at aufgabe2.CtFileReader.readFileSwitch(CtFileReader.java:155)
	at aufgabe2.CtFileReader.readFile(CtFileReader.java:96)
	at aufgabe2.CtFileReader.readCtFile(CtFileReader.java:36)
	at aufgabe2.CtConverter.txtAndBinToCt(CtConverter.java:50)
	at aufgabe2.CtConverterDemoMain.main(CtConverterDemoMain.java:19)
Exception in thread "main" java.lang.NumberFormatException: the file have been corrupt some data in the file wasn´t properly formatted.
	at aufgabe2.CtConverter.txtAndBinToCt(CtConverter.java:55)
	at aufgabe2.CtConverterDemoMain.main(CtConverterDemoMain.java:19)

Process finished with exit code 1

```

bei folgendem Aufruf

```
        cv.txtAndBinToCt("/home/zowallar/Schreibtisch/demo/sample-data/dataViewer2.txt","/home/zowallar/Schreibtisch/demo/sample-data/dataViewer2.bin");
```

mit den Daten aus dem ILIAS.

- Maven Build schlägt fehl in der Test-Phase


```
[INFO] Running aufgabe2.CtConverterTest
Test Folder is at : /home/zowallar/IdeaProjects/gruppe09/CtConverter/ConvertedFiles
22.11.2022, 16:02:22 INFO: aufgabe2.CtFileReader.readCtFile: trying to read for object:aufgabe2.Person@2fd6b6c7
22.11.2022, 16:02:22 ERROR: aufgabe2.CtFileReader.readFile: Failure at Reading Txt- or CT-File
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.045 s <<< FAILURE! - in aufgabe2.CtConverterTest
[ERROR] aufgabe2.CtConverterTest  Time elapsed: 0.045 s  <<< ERROR!
java.lang.RuntimeException
	at aufgabe2.CtFileReader.readFile(CtFileReader.java:101)
	at aufgabe2.CtFileReader.readCtFile(CtFileReader.java:59)
	at aufgabe2.CtConverterTest.setupPerson(CtConverterTest.java:37)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
	at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
	at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
	at org.junit.jupiter.engine.extension.TimeoutExtension.interceptLifecycleMethod(TimeoutExtension.java:128)
	at org.junit.jupiter.engine.extension.TimeoutExtension.interceptBeforeAllMethod(TimeoutExtension.java:70)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeBeforeAllMethods$13(ClassBasedTestDescriptor.java:411)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeBeforeAllMethods(ClassBasedTestDescriptor.java:409)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:215)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:84)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:148)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:147)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:127)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:90)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:55)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:102)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:54)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:114)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:86)
	at org.junit.platform.launcher.core.DefaultLauncherSession$DelegatingLauncher.execute(DefaultLauncherSession.java:86)
	at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:55)
	at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:223)
	at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:175)
	at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:139)
	at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:456)
	at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:169)
	at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:595)
	at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:581
```

Zum Laden der Dateien besser den ContextClassLoader des aktuellen Threads verwenden. Dann die Testdaten auch in `src/test/resources` ablegen. Auf diese Weise bekommt man dann einen absoluten Pfad auf dem man arbeiten kann.

- Module (artifactId) sollten konsistent zum Ordnernamen auf Dateisysteme benannt werden.
- Capslock in GroupID besser vermeiden und "klein" schreiben.
- Ihr "verschluckt" sehr oft den Grund einer Exception. Besser wäre es die gefangene Exception der neu geworfenen Exception im Konstruktor zu übergeben. Auf diese Weise kann der Aufrufer dann auch den Grund für einen Fehler feststellen und verliert nicht die Stacktrace informationen der zugrunde liegenden Exception, weil nur eine RuntimeException ohne Angabe einer Message oder Exception geworfen wird.
- System.out.println in der Converter Implementierung durch Logger ersetzen!
- Das `close()` auf einem Reader/Writer gehört entweder in einen `finally`-Block **oder** in einen `try-with-resources`-Block. Wird es einfach im `try { .. }` aufgerufen, dann entsteht u.U. ein "MemoryLeak", da der Reader/Writer auf Grund einer vorangegangenen Exception nicht geschlossen werden konnte.


# Feedback 2

- Projekt baut leider nicht, weil in `CtConverter` auf eine alte `artifactId` des Loggers verwiesen wird.
- Tests werden leider auch nicht ausgeführt:

```
06.12.2022, 07:52:26 ERROR: aufgabe2.CtFileReader.readFile: Failure at Reading Txt- or CT-File
java.io.FileNotFoundException: src\test\resources\testData\ConvertedFiles\dataViewer.txt (Datei oder Verzeichnis nicht gefunden)
	at java.base/java.io.FileInputStream.open0(Native Method)
	at java.base/java.io.FileInputStream.open(FileInputStream.java:216)
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:157)
	at java.base/java.io.FileReader.<init>(FileReader.java:75)
	at aufgabe2.CtFileReader.readFile(CtFileReader.java:91)
	at aufgabe2.CtFileReader.readCtFile(CtFileReader.java:35)
	at aufgabe2.CtConverterTest.setupPerson(CtConverterTest.java:39)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
	at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
	at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
	at org.junit.jupiter.engine.extension.TimeoutExtension.interceptLifecycleMethod(TimeoutExtension.java:128)
	at org.junit.jupiter.engine.extension.TimeoutExtension.interceptBeforeAllMethod(TimeoutExtension.java:70)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeBeforeAllMethods$13(ClassBasedTestDescriptor.java:411)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeBeforeAllMethods(ClassBasedTestDescriptor.java:409)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:215)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:84)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:148)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:147)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:127)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:90)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:55)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:102)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:54)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:114)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:86)
	at org.junit.platform.launcher.core.DefaultLauncherSession$DelegatingLauncher.execute(DefaultLauncherSession.java:86)

```

Der Grund liegt daran, dass sich die Pfad-Trennzeichen `/` vs `\` zwischen verschiedenen Betriebssystemen unterscheiden. Um das Problem zu beheben, könntet ihr entweder `File.separator` verwenden.

# Aufwändigere Alternative

Alternativ folgendes Snippet, um den Context Classloader zu verwenden:

```
    private Path getResourceFromClasspath(String resource) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(resource);

        if (url == null) {
            throw new RuntimeException("Could not find resource with name: " + resource);
        } else {
            try {
                return Path.of(url.toURI()).toAbsolutePath();
            } catch (URISyntaxException e) {
                throw new RuntimeException("Could not get URI from URL: " + url);
            }
        }
    }
```

Die Schnittstellen im Writer/Reader würde ich von `String` zu `Path` anpassen. Aufruf der Methode für die Tests dann z.B.:

```
        final Path testDataViewerCt = getResourceFromClasspath("testData/testDataViewer.ct");
        assertNotNull(testDataViewerCt);

        personReadCt = cfr.readCtFile(testDataViewerCt.toString());
```

Zum Schreiben in bestimmte Ordner würde ich `@TempDir` verwenden, s.h. hier https://www.baeldung.com/junit-5-temporary-directory

## Sonstiges

Für das OK wäre es allerdings ausreichend, wenn ihr den Code auf `File.separator` migriert.

Details gerne später im direkten Gespräch.