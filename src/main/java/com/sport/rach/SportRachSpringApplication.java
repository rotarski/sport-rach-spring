package com.sport.rach;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


import com.sport.rach.klub.dao.CzlonekRepository;
import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.TestDao;
import com.sport.rach.klub.model.Czlonek;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.main.dao.ItemContainerRepository;
import com.sport.rach.main.dao.MainContainerRepository;
import com.sport.rach.main.model.ItemContainer;
import com.sport.rach.main.model.MainContainer;
import com.sport.rach.rachunki.dao.PodmiotRepository;
import com.sport.rach.rachunki.model.Podmiot;
import com.sport.rach.user.dao.RoleRepository;
import com.sport.rach.user.model.ERole;
import com.sport.rach.user.model.Role;

@SpringBootApplication
public class SportRachSpringApplication {
	
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}


	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = 
				SpringApplication.run(SportRachSpringApplication.class, args);
		
		Role roleUser = new Role(ERole.ROLE_USER);
		Role roleAdmin = new Role(ERole.ROLE_ADMIN);
		Role roleModer = new Role(ERole.ROLE_MODERATOR);
		
		RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
		roleRepository.save(roleUser);
		roleRepository.save(roleAdmin);
		roleRepository.save(roleModer);
		
		TestDao testDao = ctx.getBean(TestDao.class);
		testDao.createUsersWithKlubs();
		testDao.addRachunki();
		testDao.addRachunki2();
		
//		KlubRepository klubRepository = ctx.getBean(KlubRepository.class);
//		System.out.println("**********find klub");
//		Optional<Klub> klubOp = klubRepository.findById(1L);
//		System.out.println("**********end find klub");
//		Long id = 0L;
//		if(klubOp.isPresent()) {
//			
//			Klub klub = klubOp.get();
//			id = klub.getNadzor().getId();
//			CzlonekRepository czlonekRepository = ctx.getBean(CzlonekRepository.class);
//			List<Czlonek> sklad = czlonekRepository.findSkladByIdOrgan(id);
//			
//			sklad.stream().forEach(c -> { 
//				String text = c.getNazwisko() + "-" + c.getFunkcjaOrganu();
//				System.out.println(text);
//			});
//			
//			Czlonek czlonek = sklad.stream().filter(c -> c.getNazwisko().equals("Nowak"))
//							.findFirst().orElseThrow();
//			czlonek.setNazwisko("Nowaczek");
//			System.out.println("*** save czlonek");
//			czlonekRepository.save(czlonek);
//			
//			System.out.println("*** save klub");
//			klub.getAdres().setNumer("2B");
//			klubRepository.save(klub);
//
//			
//			System.out.println("***fetch get klub");
//			
//			List<Czlonek> skladNadzor = klubRepository.getKlubFetch(1L).getNadzor().getSklad();
//			System.out.println("***after fetch get klub");
//			
			
			
			ItemContainerRepository containerRepository = ctx.getBean(ItemContainerRepository.class);
			
			List<ItemContainer> itemContainers = Arrays.asList(
			       new ItemContainer("Dla kogo?", "<style>span{color:red;}</style>Jak widać, sposobów na utworzenie klubu sportowego jest kilka. Na wybór formy zarejestrowania klubu wpływ może mieć przede wszystkim zakres działań, które chce podjąć klub sportowy. Wynika to z faktu, że kluby sportowe powinny <div><img src=\"assets/pilka.jpg\" alt=\"pilka\" height=\"500\" width=\"600\" class=\"img-fluid\"></div>realizować zadania tylko w zakresie sportu. Jest to podstawowy warunek istnienia klubu sportowego. Tylko rejestracja w KRS daje klubowi sportowemu możliwość realizacji innych działań (pozasportowych) zgodnie z prawem. Uczniowskie Kluby Sportowe, tzw. <span class=\"bg-danger\" >hejka</span>UKSy oraz kluby, które zdecydują się na rejestrację w starostwie mogą prowadzić działalność tylko i wyłącznie w zakresie sportu. Podjęcie innej działalności np. w zakresie ekologii przez te kluby (UKSy lub kluby sportowe zarejestrowane jedynie w starostwie) wymusza konieczność rejestracji w KRS.", 1),     
			       new ItemContainer("Dla kogo?", "Jak widać, sposobów na utworzenie klubu sportowego jest kilka. Na wybór formy zarejestrowania klubu wpływ może mieć przede wszystkim zakres działań, które chce podjąć klub sportowy. Wynika to z faktu, że kluby sportowe powinny realizować zadania tylko w zakresie sportu. Jest to podstawowy warunek istnienia klubu sportowego. Tylko rejestracja w KRS daje klubowi sportowemu możliwość realizacji innych działań (pozasportowych) zgodnie z prawem. Uczniowskie Kluby Sportowe, tzw. UKSy oraz kluby, które zdecydują się na rejestrację w starostwie mogą prowadzić działalność tylko i wyłącznie w zakresie sportu. Podjęcie innej działalności np. w zakresie ekologii przez te kluby (UKSy lub kluby sportowe zarejestrowane jedynie w starostwie) wymusza konieczność rejestracji w KRS.", 2),  
			       new ItemContainer("Dla kogo?", "Jak widać, sposobów na utworzenie klubu sportowego jest kilka. Na wybór formy zarejestrowania klubu wpływ może mieć przede wszystkim zakres działań, które chce podjąć klub sportowy. Wynika to z faktu, że kluby sportowe powinny realizować zadania tylko w zakresie sportu. Jest to podstawowy warunek istnienia klubu sportowego. Tylko rejestracja w KRS daje klubowi sportowemu możliwość realizacji innych działań (pozasportowych) zgodnie z prawem. Uczniowskie Kluby Sportowe, tzw. UKSy oraz kluby, które zdecydują się na rejestrację w starostwie mogą prowadzić działalność tylko i wyłącznie w zakresie sportu. Podjęcie innej działalności np. w zakresie ekologii przez te kluby (UKSy lub kluby sportowe zarejestrowane jedynie w starostwie) wymusza konieczność rejestracji w KRS.", 3),  
			       new ItemContainer("Dla kogo?", "Jak widać, sposobów na utworzenie klubu sportowego jest kilka. Na wybór formy zarejestrowania klubu wpływ może mieć przede wszystkim zakres działań, które chce podjąć klub sportowy. Wynika to z faktu, że kluby sportowe powinny realizować zadania tylko w zakresie sportu. Jest to podstawowy warunek istnienia klubu sportowego. Tylko rejestracja w KRS daje klubowi sportowemu możliwość realizacji innych działań (pozasportowych) zgodnie z prawem. Uczniowskie Kluby Sportowe, tzw. UKSy oraz kluby, które zdecydują się na rejestrację w starostwie mogą prowadzić działalność tylko i wyłącznie w zakresie sportu. Podjęcie innej działalności np. w zakresie ekologii przez te kluby (UKSy lub kluby sportowe zarejestrowane jedynie w starostwie) wymusza konieczność rejestracji w KRS.", -1)  
			                                      );
			
			itemContainers.stream().forEach(item -> {
				containerRepository.save(item);
			});
			
			MainContainerRepository mainContainerRepository =
					ctx.getBean(MainContainerRepository.class);
			List<MainContainer> mainContainers = Arrays.asList(
				       new MainContainer("Księgowość klubu sportowego", 
				    		   "Kluby sportowe mogą prowadzić swoją działalność na różny sposób. Prawo pozwala na utworzenie klubu sportowego w formie:\n" + 
				    		   "<ul><li> stowarzyszenia zarejestrowanego w KRS,</li>" + 
				    		   "<li>fundacji zarejestrowanej w KRS,</li>" + 
				    		   "<li>uczniowskiego klubu sportowego (UKS),</li>" + 
				    		   "<li>stowarzyszenia, które wpisuje się do ewidencji starosty, właściwego ze względu na miejsce, w którym klub ma siedzibę,</li>" + 
				    		   "<li>spółki kapitałowej, która podlega wpisowi do KRS (spółka ta może być np. spółką z o. o. lub spółką akcyjną)</li>" + 
				    		   "</ul>" + 
				    		   " <br>" + 
				    		   "<p>Jak widać, sposobów na utworzenie klubu sportowego jest kilka. Na wybór formy zarejestrowania klubu wpływ może mieć przede wszystkim zakres działań, które chce podjąć klub sportowy. Wynika to z faktu, że kluby sportowe powinny realizować zadania tylko w zakresie sportu. Jest to podstawowy warunek istnienia klubu sportowego. Tylko rejestracja w KRS daje klubowi sportowemu możliwość realizacji innych działań (pozasportowych) zgodnie z prawem. Uczniowskie Kluby Sportowe, tzw. UKSy oraz kluby, które zdecydują się na rejestrację w starostwie mogą prowadzić działalność tylko i wyłącznie w zakresie sportu. Podjęcie innej działalności np. w zakresie ekologii przez te kluby (UKSy lub kluby sportowe zarejestrowane jedynie w starostwie) wymusza konieczność rejestracji w KRS.</p>" + 
				    		   "Księgowość w klubie sportowym\n" + 
				    		   "Każdy klub sportowy musi prowadzić księgowość. Nie ma w tym przypadku znaczenia forma jego utworzenia (czy poprzez wpisanie do ewidencji starosty, czy poprzez rejestrację w KRS). Mylne jest więc przeświadczenie, że np. uczniowskie kluby sportowe czy kluby sportowe zarejestrowane tylko w ewidencji starosty takiej księgowości prowadzić nie muszą.\n" + 
				    		   "Nie ma znaczenia też to, że klub sportowy uzyskuje dochody tylko i wyłącznie w formie składek członkowskich pobieranych od swoich członków. Pomimo tego, że są to tylko składki klub i tak musi prowadzić księgowość. Brak prowadzenia działalności gospodarczej także nie zwolni klubu sportowego z obowiązku prowadzenia księgowości.\n" + 
				    		   "Obowiązek wynikający z ustawy o rachunkowości\n" + 
				    		   "Konieczność prowadzenia księgowości przez klub wynika m.in. z ustawy o rachunkowości i dodatkowo z ustawy o sporcie. Ustawa o rachunkowości nakazuje prowadzić księgowość każdej organizacji, która ma osobowość prawną (jest zarejestrowana w KRS) i takiej, która tej osobowości prawnej nie ma (stowarzyszenia zwykłe – którym klub sportowy może być). Prowadzenie księgowości jest m.in. potrzebne do tego, aby można było w przejrzysty sposób zapoznać się z działalnością organizacji, a przede wszystkim z jej finansami.\n" + 
				    		   "Uczniowskie kluby sportowe (UKSy) i kluby sportowe, pomimo iż nie są rejestrowane w KRS, to – zgodnie z ustawą o sporcie – mają osobowość prawną i tym samym podlegają wymogom ustawy o rachunkowości.\n" + 
				    		   "Formy prowadzenia księgowości w klubie sportowym\n" + 
				    		   "Przepisy dają możliwość wyboru formy księgowości organizacji pozarządowej, w tym klubom sportowym. Nie musi to być od razu pełna księgowość, polegająca na prowadzeniu ksiąg rachunkowych i szeregu innych dodatkowych ewidencji, jak np. prowadzenie rozrachunków z dostawcami i odbiorcami czy sporządzenie polityki rachunkowości oraz wysyłanie na żądanie organów skarbowych plików JPK.\n" + 
				    		   "Uproszczona ewidencja przychodów i kosztów\n" + 
				    		   "Klub sportowy może prowadzić pełną księgowość tzn. księgi rachunkowe lub uproszczoną księgowość, polegającą na prowadzeniu uproszczonej ewidencji przychodów i kosztów (UEPiK). W skład tej ewidencji wchodzi prowadzenie:\n" + 
				    		   "\n" + 
				    		   "    Zestawienia przychodów i kosztów (tabelka bardzo podobna do tej, którą stosują osoby fizyczne prowadzące działalność gospodarczą w formie podatkowej księgi przychodów i kosztów);\n" + 
				    		   "    Zestawienia przepływów finansowych (pomaga sporządzić na koniec roku deklarację CIT-8);\n" + 
				    		   "    Karty przychodów pracownika;\n" + 
				    		   "    Wykazu środków trwałych oraz wartości niematerialnych i prawnych.\n" + 
				    		   "\n" + 
				    		   "Dwa ostatnie zestawienia nie są obowiązkowe, jeśli klub sportowy nie zatrudnia pracowników ani zleceniobiorców oraz gdy nie posiada na stanie środków trwałych.\n" + 
				    		   "Uproszczoną ewidencję przychodów i kosztów klub sportowy może jednak prowadzić pod warunkiem, że:\n" + 
				    		   "\n" + 
				    		   "    działa w sferze zadań publicznych\n" + 
				    		   "    nie prowadzi działalności gospodarczej\n" + 
				    		   "    nie ma statusu OPP\n" + 
				    		   "    osiągnął przychody wyłącznie z:\n" + 
				    		   "\n" + 
				    		   "– działalności nieodpłatnej pożytku publicznego (składki członkowskie, darowizny, zapisy, spadki, dotacje, subwencje, zbiórki publiczne)\n" + 
				    		   "– działalności odpłatnej pożytku publicznego z tytułu sprzedaży towarów i usług\n" + 
				    		   "– tytułu sprzedaży, najmu lub dzierżawy składników majątkowych\n" + 
				    		   "– tytułu odsetek od środków pieniężnych na rachunkach bankowych\n" + 
				    		   "i wartość przychodu z powyższych źródeł nie przekroczyła w poprzednim roku 100 tys. zł. W roku rozpoczęcia działalności warunku tego się nie stosuje.",
				    		   1),     
				       new MainContainer("Dla kogo?", "Jak widać, sposobów na utworzenie klubu sportowego jest kilka. Na wybór formy zarejestrowania klubu wpływ może mieć przede wszystkim zakres działań, które chce podjąć klub sportowy. Wynika to z faktu, że kluby sportowe powinny realizować zadania tylko w zakresie sportu. Jest to podstawowy warunek istnienia klubu sportowego. Tylko rejestracja w KRS daje klubowi sportowemu możliwość realizacji innych działań (pozasportowych) zgodnie z prawem. Uczniowskie Kluby Sportowe, tzw. UKSy oraz kluby, które zdecydują się na rejestrację w starostwie mogą prowadzić działalność tylko i wyłącznie w zakresie sportu. Podjęcie innej działalności np. w zakresie ekologii przez te kluby (UKSy lub kluby sportowe zarejestrowane jedynie w starostwie) wymusza konieczność rejestracji w KRS.", -2)  
				                                      );
			mainContainers.stream().forEach(item -> {
				mainContainerRepository.save(item);
			});
			
			CzlonekRepository czlonekRepository = ctx.getBean(CzlonekRepository.class);
			boolean exists = czlonekRepository.existsCzlonekByIdAndByKlubId(1L, 1L);
			System.out.println("czlonek exists "+ exists );
			
//			PodmiotRepository pr = ctx.getBean(PodmiotRepository.class);
//			System.out.println("get podmiots");
//			List<Podmiot> podmiots = pr.finadFetchAllByKlubId(1L);
			
//			ExecutorService es = Executors.newFixedThreadPool(2);
//			
//			
//			try {
//				es.execute(()->{
//					try {
//						testDao.updateKlub();
//					} catch (Exception e) {
//						System.out.println(Thread.currentThread().getName() + 
//								" updating failed because of: " + e.getMessage());
//					}
//				});
//				
//				es.execute(()->{
//						try {
//							testDao.updateKlub2();
//
//					} catch (RuntimeException e) {
//						System.out.println("updating failed");
//					}
//				});
//		
//				es.shutdown();
//				es.awaitTermination(10, TimeUnit.SECONDS);
//			} catch (Exception e) {
//				System.out.println("updating failed");
//			}
//		}

//		KlubService service = ctx.getBean(KlubService.class);
//		service.deleteKlub(1L);
		
		
		
		
	}

}
