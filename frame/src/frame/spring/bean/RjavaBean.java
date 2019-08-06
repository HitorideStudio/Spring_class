package frame.spring.bean;


import javax.servlet.http.HttpServletRequest;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rjava/")
public class RjavaBean {
	
	@RequestMapping("connection.do")
	public String connection()throws Exception {
		
		RConnection conn = new RConnection();
		//eval() R코드 실행
		//assign() Java 데이터를 R전송하여 실행
		//REXP x = conn.eval("R.version.string");
		//REXP x1 = conn.assign(arg0, arg1);//자바에서 작업된 내용을 R로 보낼때
		
		//asBytes()벡터(일차원배열) 리턴
		//asDouble() double리턴
		//asDoubles()double배열 리턴
		//asDoubleMatrix()이차원배열 리턴
		//asList() RList 타입리턴
		//asString() 문자리턴
		//asStrings() 문자배열리턴
		//length() 데이터 수
		//System.out.println(x.asString());
		/*
		REXP x = conn.eval("length(LETTERS)"); 
		String [] lett = x.asStrings();
		for(String le : lett) {
			System.out.println(le);
		}
		
		double[] data = {1,2,3,4,5,6,7,8,9};
		conn.assign("x", data);
		conn.eval("y<- x+10");
		double[] re = conn.eval("y").asDoubles();
		for(double d : re) {
			System.out.println(d);
		}
		*/
		REXP x = conn.eval("x<-data.frame(LETTERS[1:10],c(1:10), stringAsFactors=F)");
		RList list = x.asList();
		System.out.println(list.size());
		System.out.println(list.at(0).length());
		//String [] s = list.at(0).asStrings();
		
		String [][] s = new String[list.size()][];
		for (int i = 0; i<list.size(); i++) {
			s[i] = list.at(i).asStrings();
		}
		for (int i = 0; i<list.size(); i++) {
			for(int j =0; j <list.at(0).length();j++) {
				System.out.print(s[i][j]+" ");
			}
			System.out.println();
		}
		
		conn.close(); //필수
		
		return "/rjava/connection";
	}
	
	@RequestMapping("wc2.do")
	public String wc2(Model model) throws Exception{
		RConnection conn = new RConnection();
		conn.eval("library(wordcloud2)");
		conn.eval("library(htmltools)");
		conn.eval("wc2<-wordcloud2(demoFreq, size=1, color='random-dark')");
		conn.eval("ren<-renderTags(wc2)");
		String result = conn.eval("ren$html").asString();
		model.addAttribute("result",result);
		conn.close();
		return "/rjava/wc2";
		
	}
	@RequestMapping("chart.do")
	public String chart(Model model,HttpServletRequest request) throws Exception{
	   RConnection conn = new RConnection();
	   String path = request.getRealPath("images");
	   conn.assign("path", path);
	   conn.eval("setwd(path)");
	   
	   conn.eval("png('성적.png')");
	   conn.eval("국어 <- c(4,7,6,8,5,5,9,10,4,10)");
	   conn.eval("수학 <- c(7,4,7,3,8,10,4,10,5,7)");
	   conn.eval("plot(국어,type='o',col='black',ylim=c(0,10),axes=F,ann=F)");
	   conn.eval("axis(1,at=1:10, lab=c('01','02','03','04','05','06','07','08','09','10'))");
	   conn.eval("axis(2,at=c(0,2,4,6,8,10))");
	   conn.eval("lines(수학,type='o',col='red',lyt=2,pch=16)");
	   conn.eval("box()");
	   conn.eval("title(main = '성적그래프',col.main='red')");
	   conn.eval("title(xlab = '학번')");
	   conn.eval("title(ylab = '점수')");
	   conn.eval("legend(1,10, c('국어','수학'),cex=0.8,col=c('black','red'),pch=c(16,21),lty=c(1,2))");
	   conn.eval("dev.off()");
	   
	   conn.close();
	   return "/rjava/chart";
	}
	
	@RequestMapping("tt.do")
	public String tt(HttpServletRequest request)throws Exception{
		RConnection conn = new RConnection();
		String path = request.getRealPath("images");
		conn.assign("path", path);
		conn.eval("setwd(path)");
		conn.eval("png('grade.png')");
		conn.eval("score <-read.csv('D:/Dropbox/R/score.csv')");
		
		conn.eval("class1<-subset(score,class=='1',select=c(id,math,english,science))");
		conn.eval("class2<-subset(score,class=='2',select=c(id,math,english,science))");
		conn.eval("class3<-subset(score,class=='3',select=c(id,math,english,science))");
		conn.eval("class4<-subset(score,class=='4',select=c(id,math,english,science))");
		conn.eval("class5<-subset(score,class=='5',select=c(id,math,english,science))");
		
		conn.eval("max_math <- c(max(class1$math),max(class2$math),max(class3$math),max(class4$math),max(class5$math))");
		conn.eval("max_english <- c(max(class1$english),max(class2$english),max(class3$english),max(class4$english),max(class5$english))");
		conn.eval("max_science <- c(max(class1$science),max(class2$science),max(class3$science),max(class4$science),max(class5$science))");

		conn.eval("min_math <- c(min(class1$math),min(class2$math),min(class3$math),min(class4$math),min(class5$math))");
		conn.eval("min_english <- c(min(class1$english),min(class2$english),min(class3$english),min(class4$english),min(class5$english))");
		conn.eval("min_science <- c(min(class1$science),min(class2$science),min(class3$science),min(class4$science),min(class5$science))");

		conn.eval("maxScore <- c(max_math,max_english,max_science)");
		conn.eval("minScore <- c(min_math,min_english,min_science)");
		
		conn.eval("class1m<-mean(class1$math)");
		conn.eval("class1e<-mean(class1$english)");
		conn.eval("class1s<-mean(class1$science)");
		conn.eval("mean(class1m,class1e,class1s)");
		conn.eval("class1<-colMeans(subset(score,class=='1',select=c(math,english,science)))");
		conn.eval("class2<-colMeans(subset(score,class=='2',select=c(math,english,science)))");
		conn.eval("class3<-colMeans(subset(score,class=='3',select=c(math,english,science)))");
		conn.eval("class4<-colMeans(subset(score,class=='4',select=c(math,english,science)))");
		conn.eval("class5<-colMeans(subset(score,class=='5',select=c(math,english,science)))");
		conn.eval("반평균<-data.frame(class1,class2,class3,class4,class5)");
		conn.eval("b_chart<-barplot(as.matrix(반평균),type = 'o',col=rainbow(3), main='반별과목평균', ylab='점수', beside=TRUE, ylim=c(0,100))");
		conn.eval("legend(1,30, c('수학','영어','과학'),cex=0.8,col=c('red','green','blue'),pch=c(16,21),lty=c(1,2))");
		conn.eval("text(x=b_chart,y=35,labels=paste(maxScore),cex=0.7)");
		conn.eval("text(x=b_chart,y=10,labels=paste(minScore),cex=0.7)");
		conn.eval("dev.off()");
		conn.close();
		
		return "/rjava/tt";
	}

}

