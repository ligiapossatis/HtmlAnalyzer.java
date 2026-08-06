import java.net.URL;
import java.io.*;
import java.util.Stack;

public class HtmlAnalyzer {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("URL connection error");
            return;
        }

        String urlString = args[0];

        try {
            URL url = new URL(urlString);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(url.openStream(), "UTF-8")
            );

            Stack<String> stack = new Stack<>();
            int maxDepth = -1;
            String result = null;

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("<")) {
                    // É uma tag
                    if (line.startsWith("</")) {
                        // Tag de fechamento
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                    } else {
                        // Tag de abertura
                        stack.push(line);  // por enquanto só pra contar profundidade
                    }
                } else {
                    // É texto!
                    int currentDepth = stack.size();
                    if (currentDepth > maxDepth) {
                        maxDepth = currentDepth;
                        result = line;
                    }
                }
            }

            br.close();

            if (result == null) {
                System.out.println("");
            } else {
                System.out.println(result);
            }

        } catch (Exception e) {
            System.out.println("URL connection error");
        }
    }
}
