package in.nilesh.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
 * Abstract Observable
 */
interface NewsPublisher {

	public void subscriberOberserver(NewsSubscriber subscriber);

	public void unSubsubscribeOberver(NewsSubscriber subscriber);

	public void notifySubscribers();

	public String getBreakingNews();

	public void setBreakingNews(String news);
}

/*
 * Abstract Observer
 */
interface NewsSubscriber {
	public void update(NewsPublisher publisher);
}

/*
 * Concrete Observable
 */
class BussinessNewsPublisher implements NewsPublisher {

	private List<NewsSubscriber> subscribers = new ArrayList<>();
	private String breakingNews;

	public void subscriberOberserver(NewsSubscriber subscriber) {
		subscribers.add(subscriber);
	}

	public void unSubsubscribeOberver(NewsSubscriber subscriber) {
		subscribers.remove(subscriber);
	}

	public void notifySubscribers() {
		for (NewsSubscriber subsriber : subscribers) {
			subsriber.update(this);
		}
	}

	public void setBreakingNews(String news) {
		this.breakingNews = news;
		notifySubscribers();
	}

	public String getBreakingNews() {
		return breakingNews;
	}
}

/*
 * Concrete Observer
 */
class EmailSubscriber implements NewsSubscriber {

	public void update(NewsPublisher publisher) {
		System.out.println("EMAILING : " + publisher.getBreakingNews());
	}
}

/*
 * Concrete Observer
 */
class SMSSubscriber implements NewsSubscriber {

	public void update(NewsPublisher publisher) {
		System.out.println("SMSING : " + publisher.getBreakingNews());
	}
}

public class ObserverDesignPattern {
	public static void main(String[] args) {
		NewsPublisher myBizNews = new BussinessNewsPublisher();

		NewsSubscriber emailSubscriber = new EmailSubscriber();
		NewsSubscriber smsSubscriber = new SMSSubscriber();

		myBizNews.subscriberOberserver(emailSubscriber);
		myBizNews.subscriberOberserver(smsSubscriber);

		myBizNews.setBreakingNews("Salman Khan's dog commited sucide after watching Jay Ho");

		myBizNews.unSubsubscribeOberver(emailSubscriber);
		myBizNews.setBreakingNews("CID completed 3 million episodes today");

	}
}
